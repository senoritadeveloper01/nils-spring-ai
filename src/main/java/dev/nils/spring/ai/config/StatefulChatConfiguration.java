package dev.nils.spring.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.DEFAULT_CHAT_MEMORY_CONVERSATION_ID;

@Configuration
public class StatefulChatConfiguration {

    @Bean
    ChatClient statefulChatClient(ChatClient.Builder chatClientBuilder, ChatMemory statefulChatMemory) {
        return chatClientBuilder
                .defaultAdvisors(
                        // Chat memory helps us keep context when using the chatbot for up to 10 previous messages.
                        new MessageChatMemoryAdvisor(statefulChatMemory, DEFAULT_CHAT_MEMORY_CONVERSATION_ID, 10),
                        new SimpleLoggerAdvisor()
                )
                .build();
    }

    @Bean
    public ChatMemory statefulChatMemory() {
        return new InMemoryChatMemory();
    }
}
