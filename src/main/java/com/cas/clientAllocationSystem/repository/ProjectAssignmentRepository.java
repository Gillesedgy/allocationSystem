package com.cas.clientAllocationSystem.repository;

import com.cas.clientAllocationSystem.entity.ProjectAssignment;
import com.cas.clientAllocationSystem.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {

    List<ProjectAssignment> findByProjectId(Long projectId);

    @Query("SELECT s FROM Staff s WHERE s.id NOT IN (SELECT pa.staff.id FROM ProjectAssignment pa WHERE pa.endDate IS NULL OR pa.endDate > CURRENT_DATE)")
    List<Staff> findAvailableStaff();

    @Query("SELECT pa FROM ProjectAssignment pa WHERE pa.staff.id = :staffId AND (pa.endDate IS NULL OR pa.endDate > CURRENT_DATE)")
    List<ProjectAssignment> findCurrentAssignments(Long staffId);
}
// WITHOUT QUERY

/*  List<ProjectAssignment> findByProjectId(Long projectId);

    // Find staff members without ongoing assignments
    List<ProjectAssignment> findByEndDateIsNullOrEndDateAfter(java.time.LocalDate currentDate);

    List<ProjectAssignment> findByStaffId(Long staffId);*/


//package com.cas.clientAllocationSystem.repository;
//
//import com.cas.clientAllocationSystem.entity.ProjectAssignment;
//import com.cas.clientAllocationSystem.entity.Staff;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.Date;
//import java.util.List;
//
//@Repository
//public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Long> {
//    List<ProjectAssignment> findByProjectId(Long projectId);
//
//
//    List<Staff> findAvailableStaff();
//
//    List<ProjectAssignment> findCurrentAssignments(Long staffId);
//
//
//}
