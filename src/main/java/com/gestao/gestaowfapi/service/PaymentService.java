package com.gestao.gestaowfapi.service;

import com.gestao.gestaowfapi.dto.PaymentDTO;
import com.gestao.gestaowfapi.model.Payment;
import reactor.core.publisher.Mono;

public interface PaymentService {

    Mono<Payment> process(PaymentDTO dto);

}
