package com.example.demo.consumer;


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
    public void receive(AccountRequestDTO data){
        System.out.println(">>> Kafka Recebeu" + data.ownerName());

        Account entity = new Account();
        entity.setOwnerName(data.ownerName());
        entity.setAddress(data.Adress());
        entity.setTel(data.Tel());

        repository.save(entity);
        System.out.println(">>> Gravado no banco");
    }

}
