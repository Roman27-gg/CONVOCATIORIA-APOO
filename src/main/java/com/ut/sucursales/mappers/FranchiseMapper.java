package com.ut.sucursales.mappers;

import com.ut.sucursales.model.dto.FranchiseDto;
import com.ut.sucursales.model.entity.Franchise;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FranchiseMapper {

    Franchise toEntity(FranchiseDto franchiseDto);

    FranchiseDto toDto(Franchise franchise);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Franchise partialUpdate(FranchiseDto franchiseDto, @MappingTarget Franchise franchise);
}