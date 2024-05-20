package com.gestao.gestaowfapi.controller;

import com.gestao.gestaowfapi.dto.CustomerDTO;
import com.gestao.gestaowfapi.model.Customer;
import com.gestao.gestaowfapi.repository.CustomerRepository;
import com.gestao.gestaowfapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<Mono<Void>> create(@RequestBody CustomerDTO customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customerDto).then());
    }

    @GetMapping
    public ResponseEntity<Flux<Customer>> findAll(
            @RequestParam(value = "firstName",required = false, defaultValue = "") String firstName,
            @RequestParam(value = "email",required = false, defaultValue = "") String email,
            @RequestParam(value = "cpf",required = false, defaultValue = "") String cpf,
            @RequestParam(value = "pageNumber",required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize",required = false, defaultValue = "5") int pageSize,
            @RequestParam(value = "sort",required = false, defaultValue = "asc") String order
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll(firstName, email, cpf, pageNumber, pageSize, order));

    }
}
