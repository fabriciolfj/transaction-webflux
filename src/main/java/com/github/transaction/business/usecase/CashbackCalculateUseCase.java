package com.github.transaction.business.usecase;

import com.github.transaction.business.providers.ProcessCashbackProvider;
import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class CashbackCalculateUseCase {

    private final ProcessCashbackProvider cashbackProvider;

    public Mono<Void> execute(final Mono<TransactionMovementEntity> entity) {
        return entity
                .filter(TransactionMovementEntity::isTransactionDebit)
                .doOnNext(t -> log.info("transaction is debit, execute cashback {}", t.getTransaction()))
                .flatMap(t -> cashbackProvider.process(Mono.just(t)))
                .doOnNext(t -> log.info("send message cashback success {}", t.getTransaction()))
                .switchIfEmpty(Mono.defer(Mono::empty))
                .then();
    }
}
