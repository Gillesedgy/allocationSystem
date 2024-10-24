package com.cas.clientAllocationSystem.service;

import com.cas.clientAllocationSystem.dto.StaffDto;
import com.cas.clientAllocationSystem.entity.Staff;
import com.cas.clientAllocationSystem.mapper.StaffMapper;
import com.cas.clientAllocationSystem.repository.StaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    private static final Logger logger = LoggerFactory.getLogger(StaffService.class);

    //     1. Find all available staff based on availableFrom date and unassigned status
    public List<Staff> findAvailableStaff() {
        return staffRepository.findAvailableStaff();
    }

    //     2. Find staff by a specific skill (Updated method call)
    public List<Staff> findStaffBySkills(String skillName) {
        return staffRepository.findStaffBySkills(skillName);
    }

    // 3. Reassign staff to a new project if needed
    public void reassignStaff(Long staffId, LocalDate newAvailableFrom) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new IllegalArgumentException("Staff not found"));
        staff.setAvailableFrom(newAvailableFrom);
        staffRepository.save(staff);
    }

    //4 . Find staff regardless of booking status
    public List<Staff> getAllStaff() {
        List<Staff> staffList = staffRepository.findAll();
        logger.info("Number of staff fetched: {}", staffList.size());
        return staffList;
    }


    public List<StaffDto> findByName(String staffName) {
        // Retrieve a list of staff members with the given name
        List<Staff> staffList = staffRepository.findByName(staffName);

        if (staffList.isEmpty()) {
            throw new IllegalArgumentException("No staff found with name: " + staffName);
        }

        // Map the list of Staff entities to StaffDto
        return staffList.stream()
                .map(StaffMapper.INSTANCE::toStaffDto)
                .collect(Collectors.toList());
    }
}

