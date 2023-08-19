package com.github.transaction.providers.message.sendcashback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.transaction.business.providers.ProcessCashbackProvider;
import com.github.transaction.entities.TransactionMovementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProcessCashbackProviderImpl  implements ProcessCashbackProvider {

    private final StreamBridge streamBridge;
    private final ObjectMapper objectMapper;
    @Value("{topic.cashback}")
    private String topic;

    @Override
    public Mono<TransactionMovementEntity> process(Mono<TransactionMovementEntity> entityMono) {
        return entityMono.map(CashbackConverterDTO::toDTO)
                .map(c -> {
                    try {
                        return objectMapper.writeValueAsString(c);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }).map(v -> streamBridge.send(topic, v))
                .zipWith(entityMono)
                .map(Tuple2::getT2);
    }
}
