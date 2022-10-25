package io.codeex.websocket.mapper;


import io.codeex.websocket.dto.MessageDTO;
import io.codeex.websocket.entity.Message;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MessageMapper {
    private final ModelMapper modelMapper;

    public MessageMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Message toEntity(MessageDTO dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Message.class);
    }

    public MessageDTO toDto(Message nodeEntity) {
        return Objects.isNull(nodeEntity) ? null : modelMapper.map(nodeEntity, MessageDTO.class);
    }

}
