package com.example.demo.dto;

import java.math.BigDecimal;


public record AccountResponseDTO (
    Long id,
    String ownerName, //Nome do dono da conta
    BigDecimal balance,
    String Tel,
    String Adress
) {}
