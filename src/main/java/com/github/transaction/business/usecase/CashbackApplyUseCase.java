package com.github.transaction.business.usecase;

import com.github.transaction.business.UpdateTransactionProvider;
import com.github.transaction.business.providers.DebitCashbackProvider;
import com.github.transaction.business.providers.FindCashbackProvider;
import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CashbackApplyUseCase {

    private final FindCashbackProvider provider;
    private final UpdateTransactionProvider updateTransactionProvider;
    private final DebitCashbackProvider debitCashbackProvider;

    public Mono<Void> execute(final TransactionMovementEntity entity) {
        return provider.process(entity.getCodeCustomer())
                .map(entity::applyDiscount)
                .flatMap(e -> updateTransactionProvider.process(Mono.just(e)))
                .flatMap(e -> debitCashbackProvider.process(Mono.just(e)))
                .doOnNext(e -> log.info("use cashback to transaction {}, customer {}, value {}, success",
                        entity.getTransaction(), entity.getCustomer(), entity.getCashback()))
                .then();

    }
}
