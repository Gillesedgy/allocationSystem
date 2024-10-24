package com.cas.clientAllocationSystem.controller;

import com.cas.clientAllocationSystem.dto.StaffDto;
import com.cas.clientAllocationSystem.entity.Staff;
import com.cas.clientAllocationSystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }


    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    //    @GetMapping("/{staffId}")
    @GetMapping("/name/{name}")
    public List<StaffDto> getStaffByName(@PathVariable String name) {
        return staffService.findByName(name);
    }


    @GetMapping("/booked")
    public List<Staff> getStaffBySkills(String skillName) {
        return staffService.findStaffBySkills(skillName);
    }

    @GetMapping("/unbooked")
    public List<Staff> getUnbookedStaff() {
        return staffService.findAvailableStaff();
    }
}
