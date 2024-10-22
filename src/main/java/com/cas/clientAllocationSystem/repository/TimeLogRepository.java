package com.cas.clientAllocationSystem.repository;

import com.cas.clientAllocationSystem.entity.TimeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimeLogRepository extends JpaRepository<TimeLog, Long> {
    /**
     * Retrieves all time logs for a specific assignment by its ID.
     *
     * @param assignmentId The unique ID of the project assignment.
     * @return A list of all TimeLog entries linked to the specified assignment.
     */
    List<TimeLog> findByAssignmentId(Long assignmentId);

    /**
     * Checks if a time log entry exists for a specific assignment on a given date.
     *
     * @param assignmentId The ID of the assignment.
     * @param date         The specific date to check.
     * @return true if a time log exists for the given assignment and date, false otherwise.
     */
    boolean existsByAssignmentIdAndDate(Long assignmentId, LocalDate date);

    /**
     * Sums the total hours worked for a specific assignment.
     *
     * @param assignmentId The ID of the assignment.
     * @return An Optional containing the total hours worked if logs exist, or empty if none.
     */
    Optional<Double> findSumHoursWorkedByAssignment_Id(Long assignmentId);

    /**
     * Retrieves all time logs linked to a specific client within a given date range.
     * <p>
     * Uses nested property traversal: TimeLog -> Assignment -> Project -> Contract -> Client.
     *
     * @param clientId  The ID of the client.
     * @param startDate The starting date of the range (inclusive).
     * @param endDate   The ending date of the range (inclusive).
     * @return A list of TimeLog entries within the specified date range for the client.
     */
    List<TimeLog> findByAssignment_Project_Contract_Client_IdAndDateBetween(
            Long clientId, LocalDate startDate, LocalDate endDate
    );
}
