package com.basis67.dbkits.controllers;

import com.basis67.dbkits.model.mongo.LogEntry;
import com.basis67.dbkits.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${openapi.restapi.base-path:/api/}logs")
@Tag(name = "日志管理", description = "日志记录和查询相关接口")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @PostMapping
    @Operation(summary = "创建新日志", description = "记录新的系统日志")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "日志创建成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误")
    })
    public ResponseEntity<LogEntry> createLog(
            @Parameter(description = "操作类型", required = true) @RequestParam String action,
            @Parameter(description = "用户ID", required = true) @RequestParam String userId,
            @Parameter(description = "详细信息", required = true) @RequestParam String details) {

        LogEntry newLog = logService.createLog(action, userId, details);
        return new ResponseEntity<>(newLog, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取日志", description = "获取指定ID的日志详情")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取日志"),
            @ApiResponse(responseCode = "404", description = "日志不存在")
    })
    public ResponseEntity<LogEntry> getLogById(
            @Parameter(description = "日志ID", required = true) @PathVariable String id) {

        Optional<LogEntry> log = logService.getLogById(id);
        return log.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "获取用户日志", description = "获取指定用户的所有日志")
    public ResponseEntity<List<LogEntry>> getLogsByUserId(
            @Parameter(description = "用户ID", required = true) @PathVariable String userId) {

        List<LogEntry> logs = logService.getLogsByUserId(userId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/search")
    @Operation(summary = "搜索日志", description = "根据关键词搜索日志")
    public ResponseEntity<List<LogEntry>> searchLogs(
            @Parameter(description = "搜索关键词", required = true) @RequestParam String keyword) {

        List<LogEntry> logs = logService.searchLogs(keyword);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/action")
    @Operation(summary = "根据操作类型查询日志", description = "查询指定时间段内特定操作类型的日志")
    public ResponseEntity<List<LogEntry>> getLogsByAction(
            @Parameter(description = "操作类型", required = true) @RequestParam String action,
            @Parameter(description = "开始时间", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @Parameter(description = "结束时间", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        List<LogEntry> logs = logService.getLogsByAction(action, start, end);
        return ResponseEntity.ok(logs);
    }

    @DeleteMapping("/cleanup")
    @Operation(summary = "清理旧日志", description = "删除指定日期之前的所有日志")
    public ResponseEntity<Void> cleanupOldLogs(
            @Parameter(description = "清理截止日期", required = true) @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime before) {

        logService.deleteOldLogs(before);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "获取所有日志", description = "获取系统中的所有日志")
    public ResponseEntity<List<LogEntry>> getAllLogs() {
        List<LogEntry> logs = logService.getAllLogs();
        return ResponseEntity.ok(logs);
    }
}