package com.ut.sucursales.service.impl;

import com.ut.sucursales.model.dto.BranchDto;
import com.ut.sucursales.service.interfaces.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    @Override
    public BranchDto updateBranch(UUID id, BranchDto branchDto) {
        return null;
    }

    @Override
    public List<BranchDto> getAll() {
        return List.of();
    }

    @Override
    public BranchDto getById(UUID id) {
        return null;
    }

    @Override
    public BranchDto createBranch(BranchDto branchDto) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public BranchDto updateFieldBranch(UUID id, String field, String value) {
        return null;
    }
}
