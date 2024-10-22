package com.cas.clientAllocationSystem.service;

import com.cas.clientAllocationSystem.entity.ProjectAssignment;
import com.cas.clientAllocationSystem.entity.TimeLog;
import com.cas.clientAllocationSystem.repository.ProjectAssignmentRepository;
import com.cas.clientAllocationSystem.repository.TimeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeLogService {
    @Autowired
    private TimeLogRepository timeLogRepository;
    @Autowired
    private ProjectAssignmentRepository assignmentRepository;


    // 1. Add a new time log for a specific assignment and date
    @Transactional
    public void logHours(Long assignmentId, LocalDate date, Double hours, String description) {
        // Validate assignment
        ProjectAssignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));

        // Check if a time log already exists for the given date
        if (timeLogRepository.existsByAssignmentIdAndDate(assignmentId, date)) {
            throw new IllegalArgumentException("Time log for this date already exists");
        }

        // Create and save the time log
        TimeLog timeLog = new TimeLog(null, assignment, date, hours, description);
        timeLogRepository.save(timeLog);
    }

    // 2. Get all time logs for a specific assignment
    public List<TimeLog> getTimeLogsForAssignment(Long assignmentId) {
        return timeLogRepository.findByAssignmentId(assignmentId);
    }

    // 3. Get total hours logged for an assignment (used in invoicing)
    public Double getTotalHoursForAssignment(Long assignmentId) {
        return timeLogRepository.sumHoursByAssignmentId(assignmentId)
                .orElse(0.0);
    }
}

