package com.example.icesitrade.repository;

import com.example.icesitrade.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE " +
            "(m.sender.id = :user1Id AND m.receiver.id = :user2Id) OR " +
            "(m.sender.id = :user2Id AND m.receiver.id = :user1Id) " +
            "ORDER BY m.timestamp ASC")
    List<Message> findMessagesBetweenUsers(Long user1Id, Long user2Id);
    List<Message> findBySenderId(Long senderId);
    List<Message> findByReceiverId(Long receiverId);
}
