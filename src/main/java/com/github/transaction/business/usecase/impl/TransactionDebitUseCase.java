package com.github.transaction.business.usecase.impl;

import com.github.transaction.business.usecase.TransactionUseCase;
import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service("debit")
@Slf4j
@RequiredArgsConstructor
public class TransactionDebitUseCase implements TransactionUseCase {

    @Override
    public Mono<TransactionMovementEntity> execute(final Mono<TransactionMovementEntity> entityMono) {
        return entityMono
                .doOnNext(t -> log.info("transaction execute debit {}", t.getTransaction()));
    }
}
