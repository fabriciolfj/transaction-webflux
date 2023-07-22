package com.github.transaction.entities;


import java.time.LocalDateTime;

public record TransactionMovementEntity(CustomerEntity customer,
                                        TransactionEntity transaction,
                                        LocalDateTime dateMovement,
                                        String authenticationCode) { }
