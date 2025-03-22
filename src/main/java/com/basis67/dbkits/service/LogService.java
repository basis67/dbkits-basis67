package com.basis67.dbkits.service;

import com.basis67.dbkits.model.mongo.LogEntry;
import com.basis67.dbkits.repository.LogEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogEntryRepository logEntryRepository;

    public LogEntry createLog(String action, String userId, String details) {
        LogEntry logEntry = new LogEntry(action, userId, details);
        return logEntryRepository.save(logEntry);
    }

    public Optional<LogEntry> getLogById(String id) {
        return logEntryRepository.findById(id);
    }

    public List<LogEntry> getLogsByUserId(String userId) {
        return logEntryRepository.findByUserId(userId);
    }

    public List<LogEntry> getLogsByAction(String action, LocalDateTime start, LocalDateTime end) {
        return logEntryRepository.findByActionAndCreatedAtBetween(action, start, end);
    }

    public List<LogEntry> searchLogs(String keyword) {
        return logEntryRepository.findByDetailsContaining(keyword);
    }

    public void deleteOldLogs(LocalDateTime before) {
        logEntryRepository.deleteByCreatedAtBefore(before);
    }

    public List<LogEntry> getAllLogs() {
        return logEntryRepository.findAll();
    }
}