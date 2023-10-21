package com.github.transaction.providers.cashbackservice.findcashback;

import com.github.transaction.business.providers.FindCashbackProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;

@Component
@Slf4j
@RequiredArgsConstructor
public class FindCashbackProviderImpl implements FindCashbackProvider {

    private final WebClient webClientCashback;

    @Override
    public Mono<BigDecimal> process(String customer) {
        return webClientCashback.get()
                .uri("/cashback/api/v1/" + customer)
                .retrieve()
                .bodyToMono(FindCashbackResponse.class)
                .delayElement(Duration.ofMillis(500))
                .map(FindCashbackResponse::getTotal)
                .doOnError(v -> log.info("fail consumer cashback customer {}, details {}", customer, v.getMessage()));
    }
}