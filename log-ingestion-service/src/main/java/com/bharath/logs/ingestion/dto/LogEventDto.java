package com.bharath.logs.ingestion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class LogEventDto {

    @NotNull
    private Instant timestamp;

    @NotBlank
    private String serviceName;

    @NotBlank
    private String host;

    @NotBlank
    private String level;

    @NotBlank
    private String message;

    @NotBlank
    private String traceId;

    @NotBlank
    private String environment;
}