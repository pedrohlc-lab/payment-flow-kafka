package com.example.demo.dto;

import java.math.BigDecimal;

public record AccountOperationDTO(
        Long id,
        BigDecimal amount,
        String operationType // "DEPOSIT" ou "WITHDRAW"
) {}
