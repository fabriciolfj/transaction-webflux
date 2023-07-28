package com.github.transaction.usecase;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class GenerateTokenValidateUseCase {

    public Mono<String> execute() {
        return Mono.just(RandomStringUtils.random(6))
                .doOnNext(v -> log.info("generated code"));
    }
}
