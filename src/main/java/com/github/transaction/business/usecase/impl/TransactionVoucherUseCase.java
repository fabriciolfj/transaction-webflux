package com.github.transaction.business.usecase.impl;

import com.github.transaction.business.providers.SaveTransactionProvider;
import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service("voucher")
@Slf4j
public class TransactionVoucherUseCase {

    private final SaveTransactionProvider saveTransactionProvider;

    public Mono<TransactionMovementEntity> execute(final Mono<TransactionMovementEntity> entityMono) {
        return entityMono
                .flatMap(t -> saveTransactionProvider.process(Mono.just(t)))
                .doOnNext(t -> log.info("transaction save voucher {}", t.getTransaction()));
    }
}
