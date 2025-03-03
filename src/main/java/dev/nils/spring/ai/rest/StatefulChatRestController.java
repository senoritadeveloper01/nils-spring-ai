package dev.nils.spring.ai.rest;

import dev.nils.spring.ai.service.StatefulChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StatefulChatRestController {

    private final StatefulChatService statefulChatService;

    @GetMapping(value = "api/v1/ai/stateful/generate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generate(@RequestParam(value = "promptMessage", defaultValue = "Why is the sky blue?") String promptMessage) {
        final String aiResponse = statefulChatService.generateMessage(promptMessage);
        return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
    }
}
