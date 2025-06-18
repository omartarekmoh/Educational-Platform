package com.education.platform.service;

import com.education.platform.model.chat.ChatMessage;
import java.util.List;

public interface ChatService {
    ChatMessage saveMessage(ChatMessage message);
    List<ChatMessage> getMessagesByRequestId(String requestId);
    List<ChatMessage> getMessagesByUsers(String user1Id, String user2Id);
    void deleteMessagesByRequestId(String requestId);
} 