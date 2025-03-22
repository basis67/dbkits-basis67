package com.basis67.dbkits.repository;

import com.basis67.dbkits.model.mongo.LogEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogEntryRepository extends MongoRepository<LogEntry, String> {

    List<LogEntry> findByUserId(String userId);

    List<LogEntry> findByActionAndCreatedAtBetween(String action, LocalDateTime start, LocalDateTime end);

    List<LogEntry> findByDetailsContaining(String keyword);

    void deleteByCreatedAtBefore(LocalDateTime date);
}