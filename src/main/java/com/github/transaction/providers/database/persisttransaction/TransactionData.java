package com.github.transaction.providers.database.persisttransaction;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table("transactions")
public class TransactionData {

    @Id
    @EqualsAndHashCode.Include
    private Long id;
    private String code;
    private String type;
    private BigDecimal total;
    private BigDecimal discount;
    private BigDecimal balance;
    private String status;
    private String token;
    @Column("date_token")
    private LocalDateTime dateToken;
    private String customer;
}
