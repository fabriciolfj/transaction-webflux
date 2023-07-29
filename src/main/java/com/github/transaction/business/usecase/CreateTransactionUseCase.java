package com.github.transaction.business.usecase;

import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateTransactionUseCase {

    private final Map<String, TransactionUseCase> services;

    public Mono<TransactionMovementEntity> execute(final Mono<TransactionMovementEntity> entityMono) {
        return entityMono
                .map(t -> services.get(t.getTypeTransaction()))
                .zipWith(entityMono)
                .flatMap(tuple -> tuple.getT1().execute(Mono.just(tuple.getT2())));
    }
}
