package com.github.transaction.business.providers;

import com.github.transaction.entities.TransactionMovementEntity;
import reactor.core.publisher.Mono;

public interface ProcessCashbackProvider {

    Mono<TransactionMovementEntity> process(final Mono<TransactionMovementEntity> entityMono);
}
