package io.codeex.websocket.repository;

import io.codeex.websocket.entity.Message;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface MessageRepository extends CassandraRepository<Message, Integer> {

    List<Message> findAllByFrom(String from);
    List<Message> findAllByTo(String to);
}
