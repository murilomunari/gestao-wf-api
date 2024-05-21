package com.gestao.gestaowfapi.controller;

import com.gestao.gestaowfapi.dto.PaymentDTO;
import com.gestao.gestaowfapi.model.Payment;
import com.gestao.gestaowfapi.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/credit-card")
    public ResponseEntity<Mono<Payment>> create(@Valid  @RequestBody PaymentDTO paymentDto) {
        return ResponseEntity.ok().body(paymentService.process(paymentDto));
    }

}
