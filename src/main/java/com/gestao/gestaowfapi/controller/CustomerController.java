package com.gestao.gestaowfapi.controller;

import com.gestao.gestaowfapi.dto.CustomerDTO;
import com.gestao.gestaowfapi.model.Customer;
import com.gestao.gestaowfapi.repository.CustomerRepository;
import com.gestao.gestaowfapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Mono<Void>> create(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customerDTO).then());
    }

    public ResponseEntity<Flux<Customer>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }
}
