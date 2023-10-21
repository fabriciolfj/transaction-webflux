package com.github.transaction.providers.message.sendcashbackuse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.transaction.business.providers.DebitCashbackProvider;
import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProcessCashbackUseProviderImpl implements DebitCashbackProvider {

    private final ObjectMapper mapper;
    private final StreamBridge streamBridge;
    @Value("${topic.cashbackUse}")
    private String topic;

    @Override
    public Mono<Void> process(final Mono<TransactionMovementEntity> monoEntity) {
        return monoEntity.map(CashbackUseConverterDTO::toDTO)
                .map(dto -> {
                    try {
                        return mapper.writeValueAsString(dto);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(value -> streamBridge.send(topic, value))
                .doOnNext(v -> log.info("debit use cashback {}", v))
                .then();
    }
}