package dev.nils.spring.ai.rest;

import dev.nils.spring.ai.service.FunctionCallingChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FunctionCallingChatRestController {

    private final FunctionCallingChatService functionCallingChatService;

    @PostMapping("api/v1/ai/function-calling/chat")
    public ResponseEntity<String> exchange(@RequestBody String query) {
        final String aiResponse = functionCallingChatService.generateMessage(query);
        return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
    }
}
