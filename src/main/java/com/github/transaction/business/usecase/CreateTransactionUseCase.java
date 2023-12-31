package com.github.transaction.business.usecase;

import com.github.transaction.business.providers.SaveTransactionProvider;
import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateTransactionUseCase {

    private final Map<String, TransactionUseCase> services;
    private final SaveTransactionProvider saveTransactionProvider;

    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<TransactionMovementEntity> execute(final Mono<TransactionMovementEntity> entityMono) {
        return entityMono
                .map(t -> services.get(t.getTypeTransaction()))
                .zipWith(entityMono)
                .flatMap(tuple -> tuple.getT1().execute(Mono.just(tuple.getT2())))
                .flatMap(v -> saveTransactionProvider.process(Mono.just(v)))
                .doOnNext(t -> log.info("save transaction {}", t.getTransaction()));
    }
}
