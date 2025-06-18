package com.education.platform.service;

import java.time.LocalDateTime;

public interface ZoomService {
    String createMeeting(String topic, LocalDateTime startTime, int durationMinutes);
    String getMeetingJoinUrl(String meetingId);
    void deleteMeeting(String meetingId);
    void updateMeeting(String meetingId, String topic, LocalDateTime startTime, int durationMinutes);
    String getMeetingRecordings(String meetingId);
} 