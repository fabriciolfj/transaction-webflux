package com.github.transaction.entrypoints.controller.transaction;

import com.github.transaction.business.usecase.CreateTransactionUseCase;
import com.github.transaction.entrypoints.controller.transaction.converters.TransactionConverter;
import com.github.transaction.entrypoints.controller.transaction.dto.TransactionRequest;
import com.github.transaction.entrypoints.controller.transaction.dto.TransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.github.transaction.entrypoints.controller.transaction.converters.TransactionConverter.toEntity;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/transactions/api/v1")
public class TransactionController {

    private final CreateTransactionUseCase createTransactionUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TransactionResponse> create(@Valid @RequestBody Mono<TransactionRequest> requestMono) {
        return requestMono
                .doOnNext(r -> log.info("request received {}", r))
                .map(TransactionConverter::toEntity)
                .flatMap(e -> createTransactionUseCase.execute(Mono.just(e)))
                .map(TransactionConverter::toResponse);
    }
}
