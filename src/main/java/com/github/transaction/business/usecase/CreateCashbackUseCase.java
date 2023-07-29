package com.github.transaction.business.usecase;

import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CreateCashbackUseCase {

    private final Map<String, TransactionUseCase> services;

    public TransactionMovementEntity execute(final Mono<TransactionMovementEntity> entityMono) {

    }
}
