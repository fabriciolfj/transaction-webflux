package com.github.transaction.business.providers;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface FindCashbackProvider {

    Mono<BigDecimal> process(final String customer);
}
