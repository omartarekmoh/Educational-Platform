package com.education.platform.repository;

import com.education.platform.model.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
    List<ChatMessage> findByRequestIdOrderByTimestampAsc(String requestId);
    List<ChatMessage> findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimestampAsc(
            String senderId1, String receiverId1, String senderId2, String receiverId2);
    void deleteByRequestId(String requestId);
} 