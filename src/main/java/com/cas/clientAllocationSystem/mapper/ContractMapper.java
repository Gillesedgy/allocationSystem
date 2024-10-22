package com.cas.clientAllocationSystem.mapper;

import com.cas.clientAllocationSystem.dto.ContractDto;
import com.cas.clientAllocationSystem.entity.Contract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContractMapper {
    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "rateCard.id", target = "rateCardId")
    ContractDto toContractDto(Contract contract);

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "rateCardId", target = "rateCard.id")
    Contract toContractEntity(ContractDto contractDto);
}
