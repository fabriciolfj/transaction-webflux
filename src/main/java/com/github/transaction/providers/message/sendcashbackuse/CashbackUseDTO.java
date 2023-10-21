package com.github.transaction.providers.message.sendcashbackuse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashbackUseDTO {

    private String customer;
}