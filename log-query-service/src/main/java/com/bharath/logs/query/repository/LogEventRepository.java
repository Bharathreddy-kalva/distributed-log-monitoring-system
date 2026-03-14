package com.bharath.logs.query.repository;

import com.bharath.logs.query.entity.LogEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogEventRepository extends JpaRepository<LogEvent, Long> {

    List<LogEvent> findByLevelIgnoreCase(String level);

    List<LogEvent> findByServiceNameIgnoreCase(String serviceName);

    List<LogEvent> findByEnvironmentIgnoreCase(String environment);

    List<LogEvent> findByServiceNameIgnoreCaseAndLevelIgnoreCase(String serviceName, String level);
}