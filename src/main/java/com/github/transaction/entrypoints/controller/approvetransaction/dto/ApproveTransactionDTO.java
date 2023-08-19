package com.github.transaction.entrypoints.controller.approvetransaction.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproveTransactionDTO {

    private String code;
    private String token;
}
