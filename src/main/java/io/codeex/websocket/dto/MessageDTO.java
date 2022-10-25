package io.codeex.websocket.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class MessageDTO {
    private String from;
    private String to;
    private String message;
    private LocalTime time;
}
