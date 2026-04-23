package com.example.demo.model;

import lombok.*;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String ownerName;

    @Setter(AccessLevel.NONE)
    private BigDecimal balance = BigDecimal.ZERO;

    private String tel;
    private String address;

    public boolean deposit(BigDecimal amount){
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Value should be positive");
        }
        this.balance = this.balance.add(amount);
        System.out.println("Valor" + amount.toString() + "depositado com sucesso");
        return true;
    }

    public boolean withdraw(BigDecimal amount){
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0 ){
            throw new IllegalArgumentException("Value should be positive");
        }

        if (amount.compareTo(balance) > 0){
            throw new IllegalArgumentException("Insufficient Balance");
        }

        this.balance = this.balance.subtract(amount);
        return true;
    }


}
