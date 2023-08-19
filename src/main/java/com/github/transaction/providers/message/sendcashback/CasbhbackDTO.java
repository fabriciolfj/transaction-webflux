package com.github.transaction.providers.message.sendcashback;

import java.math.BigDecimal;

public record CasbhbackDTO(String code, BigDecimal value) {
}
