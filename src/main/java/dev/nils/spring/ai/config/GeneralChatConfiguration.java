package dev.nils.spring.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralChatConfiguration {

    @Bean
    ChatClient generalChatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultAdvisors(
                        new SimpleLoggerAdvisor()
                )
                .build();
    }

    /*
    @Bean
    ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultOptions(ChatOptions.builder().temperature(0.7).build())
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
     */
}