package io.codeex.websocket.service;

import io.codeex.websocket.dto.MessageDTO;

import java.util.List;


public interface MessageService{

    MessageDTO sendMessage(MessageDTO messageDTO);

    List<MessageDTO> getHistory(String sender, String  receiver);
}
