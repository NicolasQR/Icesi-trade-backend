package com.example.icesitrade.service.impl;

import com.example.icesitrade.model.Message;
import com.example.icesitrade.repository.MessageRepository;
import com.example.icesitrade.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Message sendMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesBetweenUsers(Long user1Id, Long user2Id) {
        return messageRepository.findMessagesBetweenUsers(user1Id, user2Id);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
