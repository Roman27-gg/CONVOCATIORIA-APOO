package com.ut.sucursales.service.impl;

import com.ut.sucursales.exceptions.exceptions_class.ConflictException;
import com.ut.sucursales.mappers.FranchiseMapper;
import com.ut.sucursales.model.dto.FranchiseDto;
import com.ut.sucursales.model.dto.ProductStock;
import com.ut.sucursales.model.dto.ProductWithMoreStock;
import com.ut.sucursales.model.entity.Branch;
import com.ut.sucursales.model.entity.Franchise;
import com.ut.sucursales.repository.BranchRepository;
import com.ut.sucursales.repository.FranchiseRepository;
import com.ut.sucursales.repository.ProductRepository;
import com.ut.sucursales.service.interfaces.FranchiseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final FranchiseMapper franchiseMapper;

    @Override
    public List<FranchiseDto> getAll() {
        return franchiseRepository.findAll().stream().map(franchiseMapper::toDto).toList();
    }

    @Override
    public FranchiseDto getById(UUID id) {
        return franchiseMapper.toDto(existById(id));
    }

    @Override
    public FranchiseDto createFranchise(FranchiseDto franchiseDto) {
        if (franchiseRepository.existsByName(franchiseDto.name())) throw new ConflictException("La franquicia que intenta registrar ya existe");
        Franchise franchise = franchiseMapper.toEntity(franchiseDto);
        franchiseRepository.save(franchise);
        return franchiseMapper.toDto(franchise);
    }

    @Override
    public void deleteById(UUID id) {
        existById(id);
        franchiseRepository.deleteById(id);
    }

    @Override
    public FranchiseDto modifyName(UUID id, String name) throws ConflictException {
        if(franchiseRepository.existsByName(name)) throw new ConflictException("El nombre ya esta en uso");
        Franchise franchise = existById(id);
        franchise.setName(name);
        franchiseRepository.save(franchise);
        return franchiseMapper.toDto(franchise);
    }

    @Override
    public List<ProductWithMoreStock> getProductWithHighestStockPerBranch(UUID id) {
        List<Branch> branches = branchRepository.findByFranchise(existById(id));
        List<ProductWithMoreStock> productWithMoreStocks = new ArrayList<>();
        for(Branch branch : branches){
            List<ProductStock> products = productRepository.findProductsWithHighestStockByBranch(branch).stream()
                    .map(p -> new ProductStock(p.getName(), p.getStock())).toList();
            if(products.isEmpty()){
                productWithMoreStocks.add(new ProductWithMoreStock(branch.getName(), null));
            } else {
                productWithMoreStocks.add(new ProductWithMoreStock(branch.getName(), products));
            }
        }
        return productWithMoreStocks;
    }

    private Franchise existById(UUID id) throws EntityNotFoundException {
        return franchiseRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("La franquicia no se encuentra registrada") );
    }
}
