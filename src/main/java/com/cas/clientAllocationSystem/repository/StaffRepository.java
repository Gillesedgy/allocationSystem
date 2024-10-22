package com.cas.clientAllocationSystem.repository;

import com.cas.clientAllocationSystem.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByName(String name);

    List<Staff> findAll();


//    @Query("SELECT s FROM Staff s WHERE s.availableFrom <= CURRENT_DATE")
//    List<Staff> findAvailableStaff();
//
//    // Find staff with a specific skill
////    @Query("SELECT s FROM Staff s JOIN s.skills sk WHERE sk.name = :skillName")
//    List<Staff> findStaffBySkill(@Param("skillName") String skillName);
}
