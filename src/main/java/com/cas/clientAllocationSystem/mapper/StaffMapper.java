package com.cas.clientAllocationSystem.mapper;

import com.cas.clientAllocationSystem.dto.StaffDto;
import com.cas.clientAllocationSystem.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StaffMapper {
    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);

    StaffDto toStaffDto(Staff staff);

    Staff toStaffEntity(StaffDto staffDto);
}

