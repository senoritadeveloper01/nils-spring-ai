package dev.nils.spring.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChuckNorrisChatService {

    private final ChatClient chuckNorrisChatClient;

    public String generateJoke(String topic) {
        return chuckNorrisChatClient
                .prompt()
                .user(u -> u.text("Tell me a joke about {topic}").param("topic",topic))
                .call().content();
    }
}
