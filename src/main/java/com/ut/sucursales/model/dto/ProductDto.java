package com.ut.sucursales.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link com.ut.sucursales.model.entity.Product}
 */
public record ProductDto(@JsonProperty(access = JsonProperty.Access.READ_ONLY) UUID id,
                         @PositiveOrZero(message = "El precio del producto debe ser positivo") BigDecimal price,
                         @Size(max = 100, message = "El nombre del producto es demasiado grande")
                            @NotBlank(message = "El nombre del producto no puede ser vacio") String name,
                         @PositiveOrZero(message = "El stock del producto debe ser positivo") Integer stock,
                         @Size(max = 100, message = "El nombre de la sucursal es demasiado grande")
                            @NotBlank(message = "El nombre de la sucursal no puede ser vacio") String branchName)
        implements Serializable {
}