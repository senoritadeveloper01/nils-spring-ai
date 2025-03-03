package dev.nils.spring.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FunctionCallingChatService {

    private final ChatClient functionCallingChatClient;

    public String generateMessage(String message) {
        return this.functionCallingChatClient.prompt()
                .user(u -> u.text(message))
                .call()
                .content();
    }

}
