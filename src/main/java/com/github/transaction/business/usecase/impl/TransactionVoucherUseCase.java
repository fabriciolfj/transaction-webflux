package com.github.transaction.business.usecase.impl;

import com.github.transaction.business.usecase.TransactionUseCase;
import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service("voucher")
@Slf4j
public class TransactionVoucherUseCase implements TransactionUseCase {

    @Override
    public Mono<TransactionMovementEntity> execute(final Mono<TransactionMovementEntity> entityMono) {
        return entityMono
                .doOnNext(t -> log.info("transaction execute voucher {}", t.getTransaction()));
    }
}
