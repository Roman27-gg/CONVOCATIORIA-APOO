package com.ut.sucursales.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.ut.sucursales.model.entity.Branch}
 */
public record BranchDto(@JsonProperty(access = JsonProperty.Access.READ_ONLY) UUID id,
                        @Size(max = 100, message = "La direccion es demasiado larga")
                            @NotBlank(message = "La direccion de la sucursal no puede ser vacio") String address,
                        @Size(max = 100, message = "El nombre de la sucursal es demasiado larga")
                            @NotBlank(message = "El nombre de la sucursal no puede ser vacio") String name,
                        @NotBlank(message = "El nombre de la franquicia no puede ser vacio")
                            @Size(max = 100, message = "El nombre de la franquicia es demasiado grande") String franchiseName)
        implements Serializable {
}