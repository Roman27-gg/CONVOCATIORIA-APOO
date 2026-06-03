package com.ut.sucursales.service.impl;

import com.ut.sucursales.model.dto.ProductDto;
import com.ut.sucursales.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Override
    public ProductDto getById(UUID id) {
        return null;
    }

    @Override
    public List<ProductDto> getAll() {
        return List.of();
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto updateProduct(UUID id, ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public ProductDto updateFieldProduct(UUID id, String field, String value) {
        return null;
    }
}
