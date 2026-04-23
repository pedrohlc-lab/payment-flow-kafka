package com.example.demo.consumer;


import com.example.demo.dto.AccountOperationDTO;
import com.example.demo.dto.AccountRequestDTO;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class PaymentListener {


    private final AccountRepository repository;

    public PaymentListener(AccountRepository repository){
        this.repository = repository;
    }

    @KafkaListener(topics = "payments-topic", groupId = "payments-group")

    public void receive(AccountOperationDTO data){
        Account account =  repository.findById(data.id())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        System.out.println(">>> Kafka Recebeu " + account.getOwnerName());

        if("WITHDRAW".equalsIgnoreCase(data.operationType())){
            account.withdraw(data.amount());
        }else if ("DEPOSIT".equalsIgnoreCase(data.operationType())){
            account.deposit(data.amount());
        }else {
            System.out.println(">>> Alerta: Operação desconhecida: " + data.operationType());
            return;
        }
        repository.save(account);
        System.out.println(">>> Salvo no banco");
        }


    }


