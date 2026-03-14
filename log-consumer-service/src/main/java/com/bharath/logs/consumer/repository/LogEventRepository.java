package com.bharath.logs.consumer.repository;

import com.bharath.logs.consumer.entity.LogEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEventRepository extends JpaRepository<LogEvent, Long> {
}