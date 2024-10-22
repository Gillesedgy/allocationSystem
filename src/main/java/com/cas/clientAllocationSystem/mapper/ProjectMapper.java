package com.cas.clientAllocationSystem.mapper;

import com.cas.clientAllocationSystem.dto.ProjectDto;
import com.cas.clientAllocationSystem.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(source = "contract.id", target = "contractId")
    ProjectDto toProjectDto(Project project);

    @Mapping(source = "contractId", target = "contract.id")
    Project toProjectEntity(ProjectDto dto);
}
