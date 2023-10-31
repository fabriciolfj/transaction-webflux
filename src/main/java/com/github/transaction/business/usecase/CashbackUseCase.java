package com.github.transaction.business.usecase;

import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class CashbackUseCase {

    private final CashbackCalculateUseCase cashbackCalculateUseCase;
    private final CashbackApplyUseCase cashbackApplyUseCase;

    public Mono<TransactionMovementEntity> execute(final Mono<TransactionMovementEntity> entity) {
        return entity
                .filter(TransactionMovementEntity::isUseCashback)
                .doOnNext(t -> log.info("transaction is use cashback {}", t.getTransaction()))
                .flatMap(cashbackApplyUseCase::execute)
                .switchIfEmpty(Mono.defer(() -> cashbackCalculateUseCase.execute(entity)));
    }
}
