package io.codeex.websocket.controller;

import io.codeex.websocket.dto.MessageDTO;
import io.codeex.websocket.dto.UserDTO;
import io.codeex.websocket.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    private final MessageService messageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public MessageController(MessageService messageService, SimpMessagingTemplate simpMessagingTemplate) {
        this.messageService = messageService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/users")
    @SendTo("/chatroom/users")
    public UserDTO getUsers(@RequestBody UserDTO userDTO) {
        return userDTO;
    }

    @MessageMapping("/message")
    public MessageDTO send(@RequestBody MessageDTO messageBody) {
        System.out.println(messageBody.getMessage());
        simpMessagingTemplate.convertAndSendToUser(messageBody.getTo(), "/private", messageBody);
        simpMessagingTemplate.convertAndSendToUser(messageBody.getFrom(), "/private", messageBody);
        return messageService.sendMessage(messageBody);
    }

    @GetMapping("/messages/{sender}&{receiver}")
    public List<MessageDTO> getUserMessages(@PathVariable String sender, @PathVariable String receiver){
        return messageService.getHistory(sender, receiver);
    }
}
