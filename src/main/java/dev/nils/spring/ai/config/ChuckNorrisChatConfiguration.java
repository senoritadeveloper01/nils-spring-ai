package dev.nils.spring.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChuckNorrisChatConfiguration {

    @Bean
    ChatClient chuckNorrisChatClient(ChatClient.Builder chatClientBuilder, ChatMemory functionCallingChatMemory) {
        return chatClientBuilder
                .defaultSystem("Please respond to any question in the style of Chuck Norris.")
                .defaultAdvisors(
                        new SimpleLoggerAdvisor()
                )
                .build();
    }
}
