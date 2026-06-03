package com.ut.sucursales.service.interfaces;

import com.ut.sucursales.model.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDto getById(UUID id);

    List<ProductDto> getAll();

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(UUID id, ProductDto productDto);

    void deleteById(UUID id);

    ProductDto updateFieldProduct(UUID id, String field, String value);
}
