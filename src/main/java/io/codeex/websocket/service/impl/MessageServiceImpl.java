package io.codeex.websocket.service.impl;


import io.codeex.websocket.dto.MessageDTO;
import io.codeex.websocket.entity.Message;
import io.codeex.websocket.mapper.MessageMapper;
import io.codeex.websocket.repository.MessageRepository;
import io.codeex.websocket.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @Transactional
    public MessageDTO sendMessage(MessageDTO messageDTO) {
        messageDTO.setTime(LocalTime.now());
        Message message = messageMapper.toEntity(messageDTO);
        return messageMapper.toDto(messageRepository.save(message));
    }

    @Transactional(readOnly = true)
    public List<MessageDTO> getHistory(String sender, String receiver) {
        Stream<Message> messageStream = messageRepository.
                findAllByFrom(sender).
                stream().
                filter(a -> a.getTo().equals(receiver));
        Stream<Message> messageStream1 = messageRepository.
                findAllByTo(receiver).
                stream().
                filter(a -> a.getFrom().equals(sender));
        return Stream.concat(messageStream, messageStream1)
                .sorted(Message::compareTo)
                .map(messageMapper::toDto)
                .toList();
    }
}
