package com.bharath.logs.ingestion.controller;

import com.bharath.logs.ingestion.dto.LogEventDto;
import com.bharath.logs.ingestion.service.KafkaProducerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping
    public ResponseEntity<Map<String, String>> ingestLog(@Valid @RequestBody LogEventDto logEventDto) {
        kafkaProducerService.publishLog(logEventDto);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(Map.of("message", "Log published to Kafka"));
    }
}