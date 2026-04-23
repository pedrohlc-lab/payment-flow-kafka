package com.example.demo.producer;

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
    public String sendPayment(@RequestBody AccountRequestDTO request){
        kafkaTemplate.send("payments-topic", request);
        return "Mensagem enviada para o Kafka: " + request.ownerName();
    }

}
