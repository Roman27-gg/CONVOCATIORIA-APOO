package com.ut.sucursales.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenResponseDto(@JsonProperty(access = JsonProperty.Access.READ_ONLY) String token,
                               @JsonProperty(access = JsonProperty.Access.READ_ONLY) String type) {
}
