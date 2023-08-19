package com.github.transaction.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TokenValidationEntity {

    private String code;
    private LocalDateTime date;

    public TokenValidationEntity generetedToken() {
        this.code = RandomStringUtils.randomAlphabetic(6);
        this.date = LocalDateTime.now().plusSeconds(30);
        return this;
    }

    public boolean isTokenValid() {
        return this.date.isAfter(LocalDateTime.now());
    }

    public boolean isEqualToken(final String code) {
        return this.code.equals(code);
    }
}
