package com.github.transaction.business.usecase.impl;

import com.github.transaction.business.providers.SaveTransactionProvider;
import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service("debit")
@Slf4j
@RequiredArgsConstructor
public class TransactionDebitUseCase {

    private final SaveTransactionProvider saveTransactionProvider;

    public Mono<TransactionMovementEntity> execute(final Mono<TransactionMovementEntity> entityMono) {
        return entityMono
                .flatMap(t -> saveTransactionProvider.process(Mono.just(t)))
                .doOnNext(t -> log.info("transaction save debit {}", t.getTransaction()));
    }
}
