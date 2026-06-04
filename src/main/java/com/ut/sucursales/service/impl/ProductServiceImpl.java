package com.ut.sucursales.service.impl;

import com.ut.sucursales.mappers.ProductMapper;
import com.ut.sucursales.model.dto.ProductDto;
import com.ut.sucursales.model.entity.Branch;
import com.ut.sucursales.model.entity.Product;
import com.ut.sucursales.repository.BranchRepository;
import com.ut.sucursales.repository.ProductRepository;
import com.ut.sucursales.service.interfaces.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final BranchRepository branchRepository;

    @Override
    public ProductDto getById(UUID id) {
        return productMapper.toDto(existById(id));
    }

    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = toEntity(productDto);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto updateProduct(UUID id, ProductDto productDto) {
        existById(id);
        Product product = toEntity(productDto);
        product.setId(id);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public void deleteById(UUID id) {
        existById(id);
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto updateFieldProduct(UUID id, String field, String value) throws BadRequestException {
        Product product = existById(id);
        switch (field.toLowerCase()) {
            case "name":
                product.setName(value);
                break;
            case "price":
                try {
                    BigDecimal price = new BigDecimal(value);
                    product.setPrice(price);
                } catch (NumberFormatException e) {
                    throw new BadRequestException("El valor esperado no es valido");
                }
                break;
            case "stock":
                try {
                    product.setStock(Integer.parseInt(value));
                } catch (NumberFormatException e) {
                    throw new BadRequestException("El valor esperado no es valido");
                }
                break;
            case "branch":
                Branch branch = searchBranch(value);
                product.setBranch(branch);
                break;
            default:
                throw new BadRequestException("El valor esperado no es valido");
        }
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    private Product toEntity(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        product.setBranch(searchBranch(productDto.branchName()));
        return product;
    }

    private Branch searchBranch(String branchName) {
        return branchRepository.findByName(branchName).orElseThrow(() -> new EntityNotFoundException("La sucursal a la que intenta asignar el producto no existe"));
    }

    private Product existById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El producto no existe"));
    }
}
