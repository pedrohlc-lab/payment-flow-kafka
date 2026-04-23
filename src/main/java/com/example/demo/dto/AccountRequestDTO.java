package com.example.demo.dto;

public record AccountRequestDTO(
        Long id,
        String ownerName,
        String Tel,
        String Adress
        //Saldo nao fica no Request
){}
