package com.cas.clientAllocationSystem.mapper;


import com.cas.clientAllocationSystem.dto.ClientDto;
import com.cas.clientAllocationSystem.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDto toClientDto(Client client);

    Client toClientEntity(ClientDto clientDto);
}
