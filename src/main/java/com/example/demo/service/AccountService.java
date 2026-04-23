package com.example.demo.service;

import com.example.demo.dto.AccountRequestDTO;
import com.example.demo.dto.AccountResponseDTO;
import com.example.demo.model.Account;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.example.demo.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class AccountService {
    //CREATE
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AccountResponseDTO create(AccountRequestDTO RequestDTO) {
        Account account = new Account(RequestDTO.id(), RequestDTO.ownerName(), BigDecimal.ZERO, RequestDTO.Tel(), RequestDTO.Adress());

        Account savedAccount = repository.save(account);

        return new AccountResponseDTO(account.getId(), account.getOwnerName(), account.getBalance(), account.getTel(), account.getAddress());
    }

    //DELETE
    @Transactional
    public void delete(Long id) {
       Account account = repository.findById(id)
               .orElseThrow(() -> new RuntimeException("Account does not exists"));
    }

    //UPDATE
    @Transactional
    public AccountResponseDTO UpdateAccount(Long id, AccountRequestDTO updateRequest) {
        Account account = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        account.setOwnerName(updateRequest.ownerName());
        account.setTel(updateRequest.Tel());
        account.setAddress(updateRequest.Adress());


        return new AccountResponseDTO(account.getId(), account.getOwnerName(), account.getBalance(), account.getTel(), account.getAddress());
    }



    //READ
    @Transactional(readOnly = true)
    public List<AccountResponseDTO> findAll() {
        List<Account> accounts = repository.findAll();
        List<AccountResponseDTO> accountsResponse = new ArrayList<>();

        for (Account acc : accounts) {
            AccountResponseDTO dto = new AccountResponseDTO(
                    acc.getId(),
                    acc.getOwnerName(),
                    acc.getBalance(),
                    acc.getTel(),
                    acc.getAddress()
            );
            accountsResponse.add(dto);
        }

        return accountsResponse;
    }


    @Transactional(readOnly = true)
    public AccountResponseDTO findAccount(Long id) {
        Account acc = repository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));

        return new AccountResponseDTO(acc.getId(),
                acc.getOwnerName(),
                acc.getBalance(),
                acc.getTel(),
                acc.getAddress());

    }

    //Transacao na conta
    @Transactional
    public void updateBalance(Long id, BigDecimal amount) {

        Account account = repository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found"));
        BigDecimal newBalance = account.getBalance().add(amount);

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Insufficient Balance");
        }

        account.deposit(newBalance);
        repository.save(account);

    }

}






























