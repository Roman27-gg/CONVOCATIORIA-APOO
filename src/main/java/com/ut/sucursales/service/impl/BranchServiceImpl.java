package com.ut.sucursales.service.impl;

import com.ut.sucursales.exceptions.exceptions_class.ConflictException;
import com.ut.sucursales.mappers.BranchMapper;
import com.ut.sucursales.model.dto.BranchDto;
import com.ut.sucursales.model.entity.Branch;
import com.ut.sucursales.model.entity.Franchise;
import com.ut.sucursales.repository.BranchRepository;
import com.ut.sucursales.repository.FranchiseRepository;
import com.ut.sucursales.service.interfaces.BranchService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;
    private final FranchiseRepository franchiseRepository;

    @Override
    public BranchDto updateBranch(UUID id, BranchDto branchDto) {
        existById(id);
        Branch branch = toEntity(branchDto);
        branch.setId(id);
        branchRepository.save(branch);
        return branchMapper.toDto(branch);
    }

    @Override
    public List<BranchDto> getAll() {
        return branchRepository.findAll().stream().map(branchMapper::toDto).toList();
    }

    @Override
    public BranchDto getById(UUID id) {
        return branchMapper.toDto(existById(id));
    }

    @Override
    public BranchDto createBranch(BranchDto branchDto) {
        Branch branch = toEntity(branchDto);
        branchRepository.save(branch);
        return branchMapper.toDto(branch);
    }

    @Override
    public void deleteById(UUID id) {
        existById(id);
        branchRepository.deleteById(id);
    }

    @Override
    public BranchDto updateFieldBranch(UUID id, String field, String value) throws BadRequestException {
        Branch branch = existById(id);
        switch (field.toLowerCase()) {
            case "address":
                verifyUnique(field, value);
                branch.setAddress(value);
                break;
            case "name":
                verifyUnique(field, value);
                branch.setName(value);
                break;
            case "franchisename":
                branch.setFranchise(findFranchise(value));
                break;
            default:
                throw new BadRequestException("EL campo no existe o no se puede modificar");
        }
        branchRepository.save(branch);
        return branchMapper.toDto(branch);
    }

    private Branch existById(UUID id) {
        return branchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La sucursal que intenta buscar no existe"));
    }

    private Branch toEntity(BranchDto branchDto) {
        Branch branch = branchMapper.toEntity(branchDto);
        String franchisename = branchDto.franchiseName();
        branch.setFranchise(findFranchise(franchisename));
        return branch;
    }

    private void verifyUnique(String field, String value){
        switch (field.toLowerCase()) {
            case "name":
                if (branchRepository.existsByName(value)) throw new ConflictException("El nombre de la sucursal ya existe en el sistema");
                break;
            case "address":
                if (branchRepository.existsByAddress(value))  throw new ConflictException("La direccion de la sucursal ya existe en el sistema");
                break;
            default:
                break;
        }
    }

    private Franchise findFranchise(String name) {
        return franchiseRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("La franquicia a la que intenta relacionar no existe"));
    }
}
