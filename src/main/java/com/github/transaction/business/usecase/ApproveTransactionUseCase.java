package com.github.transaction.business.usecase;

import com.github.transaction.business.UpdateTransactionProvider;
import com.github.transaction.business.providers.FindTransactionProvider;
import com.github.transaction.business.providers.SaveTransactionProvider;
import com.github.transaction.entities.AuthorizeTransactionEntity;
import com.github.transaction.entities.TransactionMovementEntity;
import com.github.transaction.exceptions.exceptions.BusinessException;
import com.github.transaction.exceptions.enums.ErrorEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApproveTransactionUseCase {

    private final UpdateTransactionProvider updateTransactionProvider;
    private final FindTransactionProvider findTransactionProvider;
    private final CashbackUseCase cashbackUseCase;

    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<Void> execute(final Mono<AuthorizeTransactionEntity> authorizeTransactionEntityMono) {
        return findTransactionProvider.process(authorizeTransactionEntityMono)
                .zipWith(authorizeTransactionEntityMono)
                .filter(t -> t.getT1().isTokenValid(t.getT2().getToken()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(() -> new BusinessException(ErrorEnum.INVALID_TOKEN.getMessage()))))
                .doOnNext(t -> log.info("token valid to transaction {}", t.getT2().getCode()))
                .map(Tuple2::getT1)
                .map(TransactionMovementEntity::aprrovedTransaction)
                .doOnNext(t -> log.info("transaction approved {}, type {}", t.getTransaction(), t.getTypeTransaction()))
                .flatMap(v -> cashbackUseCase.execute(Mono.just(v)))
                .flatMap(t -> updateTransactionProvider.process(Mono.just(t)))
                .then();
    }
}
