package com.ut.sucursales.service.impl;

import com.ut.sucursales.exceptions.exceptions_class.ConflictException;
import com.ut.sucursales.mappers.FranchiseMapper;
import com.ut.sucursales.model.dto.FranchiseDto;
import com.ut.sucursales.model.entity.Franchise;
import com.ut.sucursales.repository.FranchiseRepository;
import com.ut.sucursales.service.interfaces.FranchiseService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FranchiseSerrviceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final FranchiseMapper franchiseMapper;

    @Override
    public List<FranchiseDto> getAll() {
        return franchiseRepository.findAll().stream().map(franchiseMapper::toDto).toList();
    }

    @Override
    public FranchiseDto getById(UUID id) {
        return franchiseMapper.toDto(franchiseRepository.getReferenceById(id));
    }

    @Override
    public FranchiseDto createFranchise(FranchiseDto franchiseDto) {
        if (franchiseRepository.existByName(franchiseDto.name())) throw new ConflictException("La franquicia que intenta registrar ya existe");
        Franchise franchise = franchiseMapper.toEntity(franchiseDto);
        franchiseRepository.save(franchise);
        return franchiseMapper.toDto(franchise);
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public FranchiseDto modifyName(UUID id, String name) {
        return null;
    }
}
