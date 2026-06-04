package com.ut.sucursales.service.interfaces;

import com.ut.sucursales.model.dto.FranchiseDto;
import com.ut.sucursales.model.dto.ProductWithMoreStock;
import org.apache.coyote.BadRequestException;


import java.util.List;
import java.util.UUID;

public interface FranchiseService {
    List<FranchiseDto> getAll();

    FranchiseDto getById(UUID id);

    FranchiseDto createFranchise(FranchiseDto franchiseDto);

    void deleteById(UUID id);

    FranchiseDto modifyName(UUID id, String name) throws BadRequestException;

    List<ProductWithMoreStock> getProductWithHighestStockPerBranch(UUID id);
}
