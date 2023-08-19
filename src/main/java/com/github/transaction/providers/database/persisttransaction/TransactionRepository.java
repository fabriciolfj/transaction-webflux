package com.github.transaction.providers.database.persisttransaction;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TransactionRepository extends ReactiveCrudRepository<TransactionData, Long> {

    Mono<TransactionData> findByCode(final String code);
}
