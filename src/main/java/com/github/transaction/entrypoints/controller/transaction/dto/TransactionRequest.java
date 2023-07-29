package com.github.transaction.entrypoints.controller.transaction.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequest {

    @NotBlank(message = "{transaction.customer")
    private String customer;
    @NotBlank(message = "{transaction.type")
    private String type;
    @NotNull(message = "{transaction.total.null")
    @Min(value = 0, message = "{transaction.total")
    private BigDecimal total;
}
