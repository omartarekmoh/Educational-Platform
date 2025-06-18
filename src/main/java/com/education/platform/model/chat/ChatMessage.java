package com.education.platform.model.chat;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ChatMessage {
    private String id;
    private String senderId;
    private String receiverId;
    private String content;
    private MessageType type;
    private LocalDateTime timestamp;
    private String requestId;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
} 