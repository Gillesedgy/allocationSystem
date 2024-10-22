package com.cas.clientAllocationSystem.controller;

import com.cas.clientAllocationSystem.entity.Staff;
import com.cas.clientAllocationSystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;


    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

//    @GetMapping("/booked")
//    public List<Staff> getStaffBySkills(String skillName) {
//        return staffService.findStaffBySkill(skillName);
//    }

//    @GetMapping("/unbooked")
//    public List<Staff> getUnbookedStaff() {
//        return staffService.getUnbookedStaff();
//    }
}
