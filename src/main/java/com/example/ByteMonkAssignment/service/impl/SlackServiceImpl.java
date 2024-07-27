package com.example.ByteMonkAssignment.service.impl;

import com.example.ByteMonkAssignment.Entity.SlackMessage;
import com.example.ByteMonkAssignment.service.ISlackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class SlackServiceImpl implements ISlackService {

    @Value("${slack.webhook.url}")
    public String webhookUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public SlackServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<String> sendMessage(SlackMessage message) {
        try{
            String payload = objectMapper.writeValueAsString(message);
            return restTemplate.postForEntity(webhookUrl, payload, String.class);
        }catch (IOException exception){
            throw new RuntimeException("Failed to convert SlackMessage to JSON", exception);
        }
    }

//    @Value("${slack.webhook.url}")
//    private String webhookUrl;
//
//    private final RestTemplate restTemplate;
//
//    public SlackServiceImpl(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public ResponseEntity<String> sendMessage(SlackMessage message) {
//        String payload = String.format("{\"text\": \"%s\"}", message.getText());
//        System.out.println(webhookUrl);
//            System.out.println(payload);
//        ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, payload, String.class);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            return ResponseEntity.ok("Message sent to Slack");
//        } else {
//            return ResponseEntity.status(response.getStatusCode()).body("Failed to send message to Slack");
//        }
//    }
}
