package com.bharath.logs.query.service;

import com.bharath.logs.query.entity.LogEvent;
import com.bharath.logs.query.repository.LogEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogQueryService {

    private final LogEventRepository logEventRepository;

    public List<LogEvent> getAllLogs() {
        return logEventRepository.findAll();
    }

    public LogEvent getLogById(Long id) {
        return logEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log not found with id: " + id));
    }

    public List<LogEvent> searchLogs(String level, String serviceName, String environment) {
        if (serviceName != null && level != null) {
            return logEventRepository.findByServiceNameIgnoreCaseAndLevelIgnoreCase(serviceName, level);
        }
        if (level != null) {
            return logEventRepository.findByLevelIgnoreCase(level);
        }
        if (serviceName != null) {
            return logEventRepository.findByServiceNameIgnoreCase(serviceName);
        }
        if (environment != null) {
            return logEventRepository.findByEnvironmentIgnoreCase(environment);
        }
        return logEventRepository.findAll();
    }
}