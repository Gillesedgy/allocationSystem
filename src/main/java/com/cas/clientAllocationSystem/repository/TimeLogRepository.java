package com.cas.clientAllocationSystem.repository;

import com.cas.clientAllocationSystem.entity.TimeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimeLogRepository extends JpaRepository<TimeLog, Long> {
    List<TimeLog> findByAssignmentId(Long assignmentId);

    boolean existsByAssignmentIdAndDate(Long assignmentId, LocalDate date);

    @Query("SELECT SUM(t.hoursWorked) FROM TimeLog t WHERE t.assignment.id = :assignmentId")
    Optional<Double> sumHoursByAssignmentId(@Param("assignmentId") Long assignmentId);



}
