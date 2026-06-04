package com.ut.sucursales.service.interfaces;

import com.ut.sucursales.model.dto.BranchDto;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface BranchService {
    BranchDto updateBranch(UUID id, BranchDto branchDto);

    List<BranchDto> getAll();

    BranchDto getById(UUID id);

    BranchDto createBranch(BranchDto branchDto);
    
    void deleteById(UUID id);

    BranchDto updateFieldBranch(UUID id, String field, String value) throws BadRequestException;
}
