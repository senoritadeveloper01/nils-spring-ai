package dev.nils.spring.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.DEFAULT_CHAT_MEMORY_CONVERSATION_ID;

@Configuration
public class FunctionCallingChatConfiguration {

    @Value("classpath:/prompts/function-calling.st")
    private Resource functionCallingPromptResource;

    @Bean
    ChatClient functionCallingChatClient(ChatClient.Builder chatClientBuilder, ChatMemory functionCallingChatMemory) {
        return chatClientBuilder
                .defaultAdvisors(
                        // Chat memory helps us keep context when using the chatbot for up to 10 previous messages.
                        new MessageChatMemoryAdvisor(functionCallingChatMemory, DEFAULT_CHAT_MEMORY_CONVERSATION_ID, 10),
                        new SimpleLoggerAdvisor()
                )
                .defaultSystem(functionCallingPromptResource)
                .defaultFunctions("listAccounts", "addAccount")
                .build();
    }

    @Bean
    public ChatMemory functionCallingChatMemory() {
        return new InMemoryChatMemory();
    }
}
