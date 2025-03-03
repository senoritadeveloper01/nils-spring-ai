package dev.nils.spring.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

// https://spring.io/blog/2024/09/26/ai-meets-spring-petclinic-implementing-an-ai-assistant-with-spring-ai-part-i

@RequiredArgsConstructor
@Service
public class StatefulChatService {

    private final ChatClient statefulChatClient;

    public String generateMessage(String message) {
        return statefulChatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
