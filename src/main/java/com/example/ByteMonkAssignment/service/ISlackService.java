package com.example.ByteMonkAssignment.service;

import com.example.ByteMonkAssignment.Entity.SlackMessage;
import org.springframework.http.ResponseEntity;

public interface ISlackService {

    ResponseEntity<String> sendMessage(SlackMessage message);
}
