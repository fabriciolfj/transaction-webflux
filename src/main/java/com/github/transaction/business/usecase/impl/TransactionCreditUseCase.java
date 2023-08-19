package com.github.transaction.business.usecase.impl;

import com.github.transaction.business.usecase.TransactionUseCase;
import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service("credit")
@RequiredArgsConstructor
@Slf4j
public class TransactionCreditUseCase implements TransactionUseCase {

    @Value("${rate.credit}")
    private String rate;

    @Override
    public Mono<TransactionMovementEntity> execute(final Mono<TransactionMovementEntity> entityMono) {
        return entityMono
                .map(t -> t.applyRate(new BigDecimal(rate)))
                .doOnNext(t -> log.info("transaction {}, apply rate execute success", t.getTransaction()));
    }
}
