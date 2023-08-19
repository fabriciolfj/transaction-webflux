package com.github.transaction.providers.database.persisttransaction;

import com.github.transaction.business.UpdateTransactionProvider;
import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateTransactionProviderImpl implements UpdateTransactionProvider {

    private final TransactionRepository transactionRepository;

    @Override
    public Mono<TransactionMovementEntity> process(Mono<TransactionMovementEntity> entityMono) {
        return entityMono
                .map(TransactionDataConverter::toData)
                .flatMap(transactionRepository::updateTransaction)
                .zipWith(entityMono)
                .then(entityMono)
                .doOnNext(t -> log.info("update transaction {}, status {}", t.getTransaction(), t.getStatus()));
    }
}
