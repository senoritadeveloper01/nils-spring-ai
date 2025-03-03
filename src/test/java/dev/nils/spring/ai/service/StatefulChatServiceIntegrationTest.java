package dev.nils.spring.ai.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

// spring-ai-spring-boot-testcontainers

// https://www.baeldung.com/spring-ai-advisors

@SpringBootTest
@EnableAutoConfiguration
@ExtendWith(SpringExtension.class)
public class StatefulChatServiceIntegrationTest {

    @Autowired
    public StatefulChatService statefulChatService;

    @Test
    void givenMessage_whenAskingChatToIncrementTheResponseWithNewName_thenNamesFromTheChatHistoryExistInResponse() {

        String responseContent = statefulChatService.generateMessage("Add this name to a list and return all the values: Bob");

        assertThat(responseContent)
                .contains("Bob");

        responseContent = statefulChatService.generateMessage("Add this name to a list and return all the values: John");

        assertThat(responseContent)
                .contains("Bob")
                .contains("John");

        responseContent = statefulChatService.generateMessage("Add this name to a list and return all the values: Anna");

        assertThat(responseContent)
                .contains("Bob")
                .contains("John")
                .contains("Anna");
    }

}
