package com.gestao.gestaowfapi.service;

import com.gestao.gestaowfapi.dto.CustomerDTO;
import com.gestao.gestaowfapi.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<Customer> create(CustomerDTO dto);

    Flux<Customer> findAll();
}
