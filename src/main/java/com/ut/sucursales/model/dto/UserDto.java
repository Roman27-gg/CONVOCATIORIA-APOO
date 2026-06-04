package com.ut.sucursales.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.ut.sucursales.model.entity.User}
 */
public record UserDto(@Size(max = 100, message = "El nombre de usuario es demsiado largo") @NotBlank String username,
                      @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) @Size(max = 100, message = "La contraseña es demsiado larga") @NotBlank String password,
                      @Email String email,
                      @JsonProperty(access = JsonProperty.Access.READ_ONLY) UUID id) implements Serializable {
}