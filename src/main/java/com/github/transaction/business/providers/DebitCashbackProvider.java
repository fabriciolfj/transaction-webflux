package com.github.transaction.business.providers;

import com.github.transaction.entities.TransactionMovementEntity;
import reactor.core.publisher.Mono;

public interface DebitCashbackProvider {

    Mono<Void> process(final Mono<TransactionMovementEntity> entity);
}
