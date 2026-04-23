package com.example.demo.producer;

import com.example.demo.dto.AccountOperationDTO;
import com.example.demo.dto.AccountRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/deposit")
    public String deposit(@RequestBody AccountOperationDTO request){
        AccountOperationDTO command = new AccountOperationDTO(
                request.id(),
                request.amount(),
                "DEPOSIT"
        );
        kafkaTemplate.send("payments-topic", command);
        return "Deposit request sent to Kafka.";

    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestBody AccountOperationDTO request){
        AccountOperationDTO command = new AccountOperationDTO(
                request.id(),
                request.amount(),
                "WITHDRAW"
        );
        kafkaTemplate.send("payments-topic", command);
        return "Withdraw request sent to Kafka";
    }

}
