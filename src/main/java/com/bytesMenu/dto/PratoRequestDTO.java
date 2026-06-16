package com.bytesMenu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PratoRequestDTO(
    @NotBlank(message = "Nome é obrigatório")
    String name,
    String description,

    @NotNull(message = "Preço é obrigatório")
    BigDecimal price,
    Boolean available

) {
}
