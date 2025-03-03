package dev.nils.spring.ai.service;

import dev.nils.spring.ai.model.ActorMovies;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatService {

    @Value("classpath:/prompts/spotify.st")
    private Resource spotifyPromptResource;

    // private final OllamaChatModel ollamaChatModel;

    private final ChatClient generalChatClient;

    public String generateMessage(String promptMessage) {
        return generalChatClient.prompt().user(promptMessage).call().content();
        // return ollamaChatModel.call(promptMessage);
    }

    public Flux<String> generateStreamMessage(String promptMessage) {
        // final Prompt prompt = new Prompt(promptMessage);
        return generalChatClient.prompt().user(promptMessage).stream().content();
        // return ollamaChatModel.stream(prompt).map(response -> response.getResult().getOutput().getText());
    }

    public String classify(String text) {
        return generalChatClient.prompt().messages(getPromptWithFewShotsHistory(text)).call().content();
        // return ollamaChatModel.call(getPromptWithFewShotsHistory(text));
    }

    private Message[] getPromptWithFewShotsHistory(String text) {
        return new Message[] {
                new SystemMessage("""
                    Classify the provided text into one of these classes.
                    
                    BUSINESS: Commerce, finance, markets, entrepreneurship, corporate developments.
                    SPORT: Athletic events, tournament outcomes, performances of athletes and teams.
                    TECHNOLOGY: innovations and trends in software, artificial intelligence, cybersecurity.
                    OTHER: Anything that doesn't fit into the other categories.
                    """),

                new UserMessage("Apple Vision Pro and the New UEFA Euro App Deliver an Innovative Entertainment Experience."),
                new AssistantMessage("TECHNOLOGY"),
                new UserMessage("Wall Street, Trading Volumes Reach All-Time Highs Amid Market Optimism."),
                new AssistantMessage("BUSINESS"),
                new UserMessage("Sony PlayStation 6 Launch, Next-Gen Gaming Experience Redefines Console Performance."),
                new AssistantMessage("TECHNOLOGY"),
                new UserMessage("Water Polo Star Secures Landmark Contract with Major League Team."),
                new AssistantMessage("SPORT"),
                new UserMessage("Culinary Travel, Best Destinations for Food Lovers This Year!"),
                new AssistantMessage("OTHER"),
                new UserMessage("UEFA Euro 2024, Memorable Matches and Record-Breaking Goals Define Tournament Highlights."),
                new AssistantMessage("SPORT"),
                new UserMessage("Rock Band Resurgence, Legendary Groups Return to the Stage with Iconic Performances."),
                new AssistantMessage("OTHER"),

                new UserMessage(text)
        };
    }

    public ActorMovies getActorMoviesByActorName(String actor) {
        return generalChatClient.prompt()
                .user(u -> u.text("Generate a filmography for the actor {actor}").param("actor", actor))
                .call()
                .entity(ActorMovies.class);
    }

    public List<ActorMovies> getActorMoviesByActorName(List<String> actorList) {
        return generalChatClient.prompt()
                .user(u -> u.text("Generate a filmography for the actors {actors}").param("actors", String.join(",", actorList)))
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }

    public String findPopularSongs(String genre) {
        return generalChatClient.prompt()
                .user(u -> u.text(spotifyPromptResource).param("genre",genre))
                .call()
                .content();
    }
}
