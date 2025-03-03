package dev.nils.spring.ai.rest;

import dev.nils.spring.ai.service.ChuckNorrisChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChuckNorrisChatRestController {

    private final ChuckNorrisChatService chuckNorrisChatService;

    @PostMapping(value = "api/v1/ai/generate/chuck-norris/joke/{topic}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateJoke(@PathVariable String topic) {
        final String aiResponse = chuckNorrisChatService.generateJoke(topic);
        return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
    }
}
