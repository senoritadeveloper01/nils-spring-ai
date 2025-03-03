package dev.nils.spring.ai.rest;

import dev.nils.spring.ai.service.RagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RagChatRestController {

    private final RagService ragService;

    @PostMapping("api/v1/ai/rag/ingestPdf")
    public ResponseEntity ingestPDF(@RequestParam(value = "fileURL") String fileURL) {
        try {
            ragService.ingestPDF(fileURL);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("[RAG_CHAT][INGEST_PDF][EXCEPTION: {}]", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("api/v1/ai/rag/query")
    public ResponseEntity<String> query(@RequestParam(value = "question") String question) {
        try {
            String response = ragService.queryLLM(question);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            log.error("[RAG_CHAT][QUERY][EXCEPTION: {}]", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
