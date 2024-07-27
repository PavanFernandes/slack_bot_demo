package com.example.ByteMonkAssignment.controller;

import com.example.ByteMonkAssignment.Entity.SlackMessage;
import com.example.ByteMonkAssignment.service.ISlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slack")
public class SlackController {

    private final ISlackService slackService;

    @Autowired
    public SlackController(ISlackService slackService) {
        this.slackService = slackService;
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendSlackMessage(@RequestBody SlackMessage message) {
        ResponseEntity<String> response = slackService.sendMessage(message);
        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok("Message sent to Slack");
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Failed to send message to Slack");
        }
    }
}
