package com.bharath.logs.consumer.consumer;

import com.bharath.logs.consumer.entity.LogEvent;
import com.bharath.logs.consumer.repository.LogEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaLogConsumer {

    private final LogEventRepository logEventRepository;

    @KafkaListener(topics = "application-logs", groupId = "log-consumer-group")
    public void consume(LogEvent logEvent) {
        log.info("Received log event: {}", logEvent);
        logEventRepository.save(logEvent);
    }
}