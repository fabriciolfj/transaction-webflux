package com.github.transaction.business.usecase;

import com.github.transaction.entities.TransactionEntity;
import com.github.transaction.entities.TransactionMovementEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CreateTransactionUseCase {

    public Mono<TransactionMovementEntity execute(final Mono<TransactionMovementEntity> entityMono) {
        return Mono.just(RandomStringUtils.random(6))
                .doOnNext(v -> log.info("generated code"));
    }
}
