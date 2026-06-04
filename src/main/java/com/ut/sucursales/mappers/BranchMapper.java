package com.ut.sucursales.mappers;

import com.ut.sucursales.model.dto.BranchDto;
import com.ut.sucursales.model.entity.Branch;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BranchMapper {
    
    Branch toEntity(BranchDto branchDto);

    @Mapping(target = "franchiseName", source = "franchise.name")
    BranchDto toDto(Branch branch);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Branch partialUpdate(BranchDto branchDto, @MappingTarget Branch branch);
}