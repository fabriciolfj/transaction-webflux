package com.github.transaction.providers.database.persisttransaction;

import com.github.transaction.business.providers.SaveTransactionProvider;
import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
@RequiredArgsConstructor
public class SaveTransactionProviderImpl implements SaveTransactionProvider {

    private final TransactionRepository repository;

    @Override
    public Mono<TransactionMovementEntity> process(Mono<TransactionMovementEntity> entityMono) {
        return entityMono
                .map(TransactionDataConverter::toData)
                .flatMap(repository::save)
                .zipWith(entityMono)
                .map(Tuple2::getT2);
    }
}
