package com.cas.clientAllocationSystem.repository;

import com.cas.clientAllocationSystem.entity.TimeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeLogRepository extends JpaRepository<TimeLog, Long> {
    List<TimeLog> findByAssignmentId(Long assignmentId);

}
