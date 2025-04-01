package com.example.icesitrade.service.impl;

import com.example.icesitrade.model.Message;
import com.example.icesitrade.model.Notification;
import com.example.icesitrade.model.User;
import com.example.icesitrade.repository.MessageRepository;
import com.example.icesitrade.repository.UserRepository;
import com.example.icesitrade.service.MessageService;
import com.example.icesitrade.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final NotificationService notificationService;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Override
    public Message sendMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());
        Message savedMessage = messageRepository.save(message);

        // Buscar el nombre del remitente desde la base de datos
        User sender = userRepository.findById(message.getSender().getId())
                .orElseThrow(() -> new RuntimeException("Remitente no encontrado"));

        // Crear notificación para el receptor
        Notification notification = Notification.builder()
                .user(message.getReceiver())
                .content("Has recibido un nuevo mensaje de " + sender.getName())
                .timestamp(LocalDateTime.now())
                .read(false)
                .build();

        notificationService.createNotification(notification);

        return savedMessage;
    }

    @Override
    public List<Message> getMessagesBetweenUsers(Long user1Id, Long user2Id) {
        return messageRepository.findMessagesBetweenUsers(user1Id, user2Id);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getReceivedMessages(Long userId) {
        return messageRepository.findByReceiverId(userId);
    }

    @Override
    public List<Message> getSentMessages(Long userId) {
        return messageRepository.findBySenderId(userId);
    }
}
