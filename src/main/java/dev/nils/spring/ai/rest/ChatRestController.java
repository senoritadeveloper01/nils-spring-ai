package dev.nils.spring.ai.rest;

import dev.nils.spring.ai.model.ActorMovies;
import dev.nils.spring.ai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatRestController {

    private final ChatService chatService;

    @GetMapping(value = "api/v1/ai/generate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generate(@RequestParam(value = "promptMessage", defaultValue = "Why is the sky blue?") String promptMessage) {
        final String aiResponse = chatService.generateMessage(promptMessage);
        return ResponseEntity.status(HttpStatus.OK).body(aiResponse);
    }

    @GetMapping(value = "api/v1/ai/stream/generate", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateStream(@RequestParam(value = "promptMessage", defaultValue = "Why is the sky blue?") String promptMessage) {
        return chatService.generateStreamMessage(promptMessage);
    }

    @PostMapping("api/v1/ai/classify")
    String classify(@RequestBody String text) {
        return chatService.classify(text);
    }

    @GetMapping("api/v1/ai/structured/movies-by-actors")
    public ResponseEntity<List<ActorMovies>> getActorMoviesByActorName(@RequestParam(value = "actors") List<String> actorList) {
        List<ActorMovies> actorMoviesList = chatService.getActorMoviesByActorName(actorList);
        return ResponseEntity.status(HttpStatus.OK).body(actorMoviesList);
    }

    @GetMapping("api/v1/ai/structured/movies-by-actor")
    public ResponseEntity<ActorMovies> getActorMoviesByActorName(@RequestParam String actor) {
        ActorMovies actorMovies = chatService.getActorMoviesByActorName(actor);
        return ResponseEntity.status(HttpStatus.OK).body(actorMovies);
    }

    @GetMapping("api/v1/ai/spotify/popular-songs")
    public ResponseEntity<String> findPopularYouTubers(@RequestParam(value = "genre", defaultValue = "jazz") String genre) {
        String songs = chatService.findPopularSongs(genre);
        return ResponseEntity.status(HttpStatus.OK).body(songs);
    }
}
