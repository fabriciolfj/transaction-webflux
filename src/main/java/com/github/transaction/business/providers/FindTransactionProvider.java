package com.github.transaction.business.providers;

import com.github.transaction.entities.AuthorizeTransactionEntity;
import com.github.transaction.entities.TransactionMovementEntity;
import reactor.core.publisher.Mono;

public interface FindTransactionProvider {

    Mono<TransactionMovementEntity> process(final Mono<AuthorizeTransactionEntity> mono);
}
