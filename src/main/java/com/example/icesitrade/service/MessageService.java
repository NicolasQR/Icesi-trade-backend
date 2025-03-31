package com.example.icesitrade.service;

import com.example.icesitrade.model.Message;
import java.util.List;

public interface MessageService {
    Message sendMessage(Message message);
    List<Message> getMessagesBetweenUsers(Long user1Id, Long user2Id);
    List<Message> getAllMessages();
}
