package com.education.platform.model.request;

import lombok.Data;

@Data
public class AssignmentRequest {
    private String subject;
    private String description;
    private String deadline;
    private String preferredEngineerId;
    private String studyLocation; // ONLINE or OFFLINE
    private String additionalNotes;
} 