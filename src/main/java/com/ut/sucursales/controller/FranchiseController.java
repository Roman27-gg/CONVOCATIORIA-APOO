package com.ut.sucursales.controller;

import com.ut.sucursales.model.dto.FranchiseDto;
import com.ut.sucursales.model.dto.ProductWithMoreStock;
import com.ut.sucursales.service.interfaces.FranchiseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/franquicias")
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseService franchiseService;

    @GetMapping()
    public List<FranchiseDto> getFranchises(){
        return franchiseService.getAll();
    }

    @GetMapping("/{id}")
    public FranchiseDto getFranchise(@PathVariable UUID id){
        return franchiseService.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public FranchiseDto createFranchise(@RequestBody @Valid FranchiseDto franchiseDto){
        return franchiseService.createFranchise(franchiseDto);
    }

    @PatchMapping("/{id}")
    public FranchiseDto modifyName(@PathVariable UUID id, @RequestParam String name) throws BadRequestException {
        return franchiseService.modifyName(id, name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFranchise(@PathVariable UUID id){
        franchiseService.deleteById(id);
    }

    @GetMapping("{id}/sucursales/producto-mayor-stock")
    public List<ProductWithMoreStock> getProductWithHighestStockPerBranch(@PathVariable UUID id){
        return franchiseService.getProductWithHighestStockPerBranch(id);
    }

}