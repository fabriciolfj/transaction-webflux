package com.github.transaction.providers.database.persisttransaction;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TransactionRepository extends ReactiveCrudRepository<TransactionData, Long> {

    Mono<TransactionData> findByCode(final String code);


    @Modifying
    @Query("update transactions set status = :#{#data.status}, balance = :#{#data.balance}, cashback = :#{#data.cashback}" +
            " where code = :#{#data.code}")
    Mono<Void> updateTransaction(final TransactionData data);
}
