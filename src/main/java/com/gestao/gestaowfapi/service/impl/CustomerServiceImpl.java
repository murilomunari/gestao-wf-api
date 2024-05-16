package com.gestao.gestaowfapi.service.impl;

import com.gestao.gestaowfapi.dto.CustomerDTO;
import com.gestao.gestaowfapi.mapper.CustomerMapper;
import com.gestao.gestaowfapi.model.Customer;
import com.gestao.gestaowfapi.repository.CustomerRepository;
import com.gestao.gestaowfapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public Mono<Customer> create(CustomerDTO dto) {
        return customerRepository.save(customerMapper.toModel(dto));
    }

    @Override
    public Flux<Customer> findAll() {
        return customerRepository.findAll();
    }
}
