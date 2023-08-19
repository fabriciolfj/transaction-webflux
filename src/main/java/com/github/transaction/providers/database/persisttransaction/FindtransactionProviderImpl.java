package com.github.transaction.providers.database.persisttransaction;

import com.github.transaction.business.providers.FindTransactionProvider;
import com.github.transaction.entities.AuthorizeTransactionEntity;
import com.github.transaction.entities.TransactionMovementEntity;
import com.github.transaction.exceptions.exceptions.TransactionNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindtransactionProviderImpl implements FindTransactionProvider {

    private final TransactionRepository repository;

    @Override
    public Mono<TransactionMovementEntity> process(Mono<AuthorizeTransactionEntity> mono) {
        return mono
                .doOnNext(t -> log.info("find transaction code {}", t.getCode()))
                .flatMap(t -> repository.findByCode(t.getCode()))
                .map(TransactionDataConverter::toEntity)
                .doOnNext(t -> log.info("transaction found {}", t.getTransaction()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new TransactionNotFoundException())));
    }
}
