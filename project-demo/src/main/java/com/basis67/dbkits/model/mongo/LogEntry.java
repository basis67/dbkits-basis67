package com.basis67.dbkits.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "logs")
public class LogEntry {

    @Id
    private String id;

    private String action;

    private String userId;

    private String details;

    private LocalDateTime createdAt;

    // 默认构造函数
    public LogEntry() {
        this.createdAt = LocalDateTime.now();
    }

    // 带参数的构造函数
    public LogEntry(String action, String userId, String details) {
        this.action = action;
        this.userId = userId;
        this.details = details;
        this.createdAt = LocalDateTime.now();
    }
}
