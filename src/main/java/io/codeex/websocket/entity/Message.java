package io.codeex.websocket.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalTime;
import java.util.UUID;

@Table("messages")
@Getter
@Setter
public class Message implements Comparable<Message> {
    @PrimaryKey
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;

    @Indexed
    String from;

    @Indexed
    String to;

    String message;

    LocalTime time;

    @Override
    public int compareTo(Message o) {
        return time.compareTo(o.getTime());
    }
}
