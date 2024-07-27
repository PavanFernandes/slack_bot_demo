package com.example.ByteMonkAssignment;

import com.example.ByteMonkAssignment.Entity.SlackMessage;
import com.example.ByteMonkAssignment.service.impl.SlackServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SlackServiceTests {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private SlackServiceImpl slackServiceImpl;

    @Value("${slack.webhook.url}")
    public String webhookUrl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();
        slackServiceImpl = new SlackServiceImpl(restTemplate, objectMapper);
        slackServiceImpl.webhookUrl = webhookUrl;
    }


    @Test
    void testSendSlackMessage(){
        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setText("welcome!");
        ResponseEntity<String> response = slackServiceImpl.sendMessage(slackMessage);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("ok");
    }
}