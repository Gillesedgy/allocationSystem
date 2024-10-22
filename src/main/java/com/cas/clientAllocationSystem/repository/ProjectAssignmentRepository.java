package com.cas.clientAllocationSystem.repository;

import com.cas.clientAllocationSystem.entity.ProjectAssignment;
import com.cas.clientAllocationSystem.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {
    List<ProjectAssignment> findByProjectId(Long projectId);

    @Query("SELECT s FROM Staff s " +
            "WHERE s.availableFrom <= CURRENT_DATE " +
            "AND NOT EXISTS (" +
            "SELECT pa FROM ProjectAssignment pa " +
            "WHERE pa.staff = s AND pa.endDate >= CURRENT_DATE)")
    List<Staff> findAvailableStaff();

//    List<ProjectAssignment> findCurrentAssignments(Long staffId);
}
