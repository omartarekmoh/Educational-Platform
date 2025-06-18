package com.education.platform.model.request;

import lombok.Data;

@Data
public class ProjectRequest {
    private String projectTitle;
    private String projectType;
    private String description;
    private String deadline;
    private String preferredEngineerId;
    private String studyLocation; // ONLINE or OFFLINE
    private String additionalNotes;
    private String[] requiredSkills;
} 