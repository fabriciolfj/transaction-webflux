package com.github.transaction.exceptions;

import com.github.transaction.exceptions.dto.ErrorDTO;
import com.github.transaction.exceptions.exceptions.BusinessException;
import com.github.transaction.exceptions.exceptions.TransactionNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<ErrorDTO>> handlerBusinessException(final BusinessException e) {
        return Mono.just(ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage())));
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public Mono<ResponseEntity<ErrorDTO>> handlerBusinessException(final TransactionNotFoundException e) {
        return Mono.just(ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage())));
    }
}
