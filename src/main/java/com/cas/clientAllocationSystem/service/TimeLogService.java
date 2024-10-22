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

    // Dependency injection via constructor for better testability and immutability
    private final TimeLogRepository timeLogRepository;
    private final ProjectAssignmentRepository assignmentRepository;

    @Autowired
    public TimeLogService(TimeLogRepository timeLogRepository,
                          ProjectAssignmentRepository assignmentRepository) {
        this.timeLogRepository = timeLogRepository;
        this.assignmentRepository = assignmentRepository;
    }

    /**
     * Logs hours worked for a specific project assignment.
     * Uses @Transactional to ensure atomic operations—if anything fails, the operation rolls back.
     *
     * @param assignmentId The ID of the project assignment.
     * @param date         The date for which hours are being logged.
     * @param hours        The number of hours worked (must be positive).
     * @param description  A description of the work performed.
     * @throws IllegalArgumentException if the assignment is not found or if a log for the same date exists.
     */
    @Transactional
    public void logHours(Long assignmentId, LocalDate date, Double hours, String description) {
        // Validate that the hours are positive
        if (hours == null || hours <= 0) {
            throw new IllegalArgumentException("Hours worked must be greater than zero.");
        }

        // Ensure that the project assignment exists
        ProjectAssignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));

        // Prevent duplicate time logs for the same date and assignment
        if (timeLogRepository.existsByAssignmentIdAndDate(assignmentId, date)) {
            throw new IllegalArgumentException("Time log for this assignment and date already exists.");
        }

        // Create and save the new time log entry
        TimeLog timeLog = new TimeLog(null, assignment, date, hours, description);
        timeLogRepository.save(timeLog);
    }

    /**
     * Retrieves all time logs for a specific project assignment.
     *
     * @param assignmentId The ID of the project assignment.
     * @return A list of TimeLog entries for the specified assignment.
     */
    public List<TimeLog> getTimeLogsForAssignment(Long assignmentId) {
        return timeLogRepository.findByAssignmentId(assignmentId);
    }

    /**
     * Calculates the total hours worked for a specific assignment.
     * This method will return 0.0 if no logs exist for the assignment.
     *
     * @param assignmentId The ID of the assignment.
     * @return The total hours worked as a Double, or 0.0 if no logs are found.
     */
    public Double getTotalHoursForAssignment(Long assignmentId) {
        return timeLogRepository.findSumHoursWorkedByAssignment_Id(assignmentId)
                .orElse(0.0);  // Default to 0.0 if no logs exist
    }

    /**
     * Retrieves all time logs for a specific client within a date range.
     *
     * @param clientId  The ID of the client.
     * @param startDate The start date of the range (inclusive).
     * @param endDate   The end date of the range (inclusive).
     * @return A list of TimeLog entries for the client within the specified date range.
     */
    public List<TimeLog> getTimeLogsForClientAndDateRange(Long clientId, LocalDate startDate, LocalDate endDate) {
        return timeLogRepository.findByAssignment_Project_Contract_Client_IdAndDateBetween(clientId, startDate, endDate);
    }
}
