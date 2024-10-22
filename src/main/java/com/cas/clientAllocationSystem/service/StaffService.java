package com.cas.clientAllocationSystem.service;

import com.cas.clientAllocationSystem.entity.Staff;
import com.cas.clientAllocationSystem.repository.StaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    private static final Logger logger = LoggerFactory.getLogger(StaffService.class);
    // 1. Find all available staff based on availableFrom date and unassigned status
//    public List<Staff> findAvailableStaff() {
//        return staffRepository.findAvailableStaff();
//    }

    // 2. Find staff by a specific skill
//    public List<Staff> findStaffBySkill(String skillName) {
//        return staffRepository.findStaffBySkill(skillName);
//    }

    // 3. Reassign staff to a new project if needed
    public void reassignStaff(Long staffId, LocalDate newAvailableFrom) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new IllegalArgumentException("Staff not found"));
        staff.setAvailableFrom(newAvailableFrom);
        staffRepository.save(staff);
    }

    //4 . Find staff regardless of booking status
    public List<Staff> getAllStaff() {
        logger.info("Fetching all staff members...");
        List<Staff> staff = staffRepository.findAll();

        if (staff.isEmpty()) {
            logger.error("No staff members found in the database.");
            throw new IllegalStateException("No staff members found.");
        }

        logger.info("Successfully fetched {} staff members.", staff.size());
        return staff;
    }
}
