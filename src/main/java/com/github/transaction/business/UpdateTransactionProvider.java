package com.github.transaction.business;

import com.github.transaction.entities.TransactionMovementEntity;
import reactor.core.publisher.Mono;

public interface UpdateTransactionProvider {

    Mono<TransactionMovementEntity> process(final Mono<TransactionMovementEntity> entityMono);
}
