package com.example.ByteMonkAssignment;

import com.example.ByteMonkAssignment.Entity.SlackMessage;
import com.example.ByteMonkAssignment.controller.SlackController;
import com.example.ByteMonkAssignment.service.ISlackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(SlackController.class)
public class SlackControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ISlackService slackService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSendSlackMessage() throws Exception {
        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setText("Welcome!");
        ResponseEntity<String> mockResponse = new ResponseEntity<>("Message sent to Slack", HttpStatus.OK);
        when(slackService.sendMessage(any(SlackMessage.class))).thenReturn(mockResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/slack/sendMessage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(slackMessage)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Message sent to Slack"));
    }
}