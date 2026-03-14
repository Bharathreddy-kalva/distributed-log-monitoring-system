package com.bharath.logs.query.controller;

import com.bharath.logs.query.entity.LogEvent;
import com.bharath.logs.query.service.LogQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogQueryController {

    private final LogQueryService logQueryService;

    @GetMapping
    public List<LogEvent> getAllLogs() {
        return logQueryService.getAllLogs();
    }

    @GetMapping("/{id}")
    public LogEvent getLogById(@PathVariable Long id) {
        return logQueryService.getLogById(id);
    }

    @GetMapping("/search")
    public List<LogEvent> searchLogs(
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String serviceName,
            @RequestParam(required = false) String environment
    ) {
        return logQueryService.searchLogs(level, serviceName, environment);
    }
}