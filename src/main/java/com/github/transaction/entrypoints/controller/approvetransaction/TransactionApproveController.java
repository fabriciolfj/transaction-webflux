package com.github.transaction.entrypoints.controller.approvetransaction;

import com.github.transaction.business.usecase.ApproveTransactionUseCase;
import com.github.transaction.entrypoints.controller.approvetransaction.converters.ApproveTransactionConverter;
import com.github.transaction.entrypoints.controller.approvetransaction.dto.ApproveTransactionDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/approve/api/v1")
public class TransactionApproveController {

    private final ApproveTransactionUseCase approveTransactionUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> process(@RequestBody final Mono<ApproveTransactionDTO> dtoMono) {
        return dtoMono
                .map(ApproveTransactionConverter::toEntity)
                .flatMap(t -> approveTransactionUseCase.execute(Mono.just(t)));
    }
}
