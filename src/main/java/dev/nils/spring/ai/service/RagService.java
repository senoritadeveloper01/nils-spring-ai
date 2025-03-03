package dev.nils.spring.ai.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RagService {

    private final VectorStore vectorStore;

    private final ChatClient ragChatClient;

    private final RetrievalAugmentationAdvisor retrievalAugmentationAdvisor;

    public void ingestPDF(String fileURL) throws MalformedURLException {
        Resource pdfResource = new FileUrlResource(URI.create(fileURL).toURL());
        // Spring AI utility class to read a PDF file page by page
        // Extract
        PagePdfDocumentReader pdfReader = new PagePdfDocumentReader(pdfResource,
                PdfDocumentReaderConfig.builder()
                        .withPageExtractedTextFormatter(ExtractedTextFormatter.builder()
                                .withNumberOfBottomTextLinesToDelete(3) // Specifies that the bottom 3 lines of text on each page should be deleted.
                                .withNumberOfTopPagesToSkipBeforeDelete(1) // Indicates that the text deletion rule should not apply to the first page.
                                .build())
                        .withPagesPerDocument(1)
                        .build());

        // Transform
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();

        log.info("[RAG_SERVICE][INGEST_PDF][Parsing document, splitting, creating embeddings, and storing in vector store...]");

        // tag as external knowledge in the vector store's metadata
        List<Document> splitDocuments = tokenTextSplitter.split(pdfReader.read());
        for (Document splitDocument: splitDocuments) { // footnotes
            splitDocument.getMetadata().put("filename", pdfResource.getFilename());
            splitDocument.getMetadata().put("version", 1);
        }

        // Sending batch of documents to vector store
        // Load
        vectorStore.write(splitDocuments);

        log.info("[RAG_SERVICE][INGEST_PDF][Done parsing document, splitting, creating embeddings and storing in vector store.]");
    }

    public String queryLLM(String question) {
        return ragChatClient.prompt()
                .advisors(retrievalAugmentationAdvisor)
                .user(question)
                .call()
                .content();
    }
}
