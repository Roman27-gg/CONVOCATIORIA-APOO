package com.ut.sucursales.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;


/**
 * DTO for {@link com.ut.sucursales.model.entity.Franchise}
 */
public record FranchiseDto(
        @Size(max = 100, message = "El nombre de la franquicia es demasiado grande")
          @NotBlank(message = "El nombre de la franquicia no puede ser vacio") String name,
        @Size(max = 100, message = "El nombre del dueño es demasiado grande")
          @NotBlank(message = "El nombre del dueño no puede ser vacio")String owner,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) UUID id
){
}