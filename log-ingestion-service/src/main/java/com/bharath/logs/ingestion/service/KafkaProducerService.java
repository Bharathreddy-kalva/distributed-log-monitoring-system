package com.bharath.logs.ingestion.service;

import com.bharath.logs.ingestion.dto.LogEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final String TOPIC_NAME = "application-logs";

    private final KafkaTemplate<String, LogEventDto> kafkaTemplate;

    public void publishLog(LogEventDto logEventDto) {
        kafkaTemplate.send(TOPIC_NAME, logEventDto.getServiceName(), logEventDto);
    }
}