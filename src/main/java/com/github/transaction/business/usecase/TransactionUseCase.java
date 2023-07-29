package com.github.transaction.business.usecase;

import com.github.transaction.entities.TransactionMovementEntity;
import reactor.core.publisher.Mono;

public interface TransactionUseCase {

    Mono<TransactionMovementEntity> execute(final Mono<TransactionMovementEntity> entityMono);
}
