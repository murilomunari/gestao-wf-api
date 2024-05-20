package com.gestao.gestaowfapi.controller;

import com.gestao.gestaowfapi.dto.CustomerDTO;
import com.gestao.gestaowfapi.dto.OrderDTO;
import com.gestao.gestaowfapi.model.Customer;
import com.gestao.gestaowfapi.model.Order;
import com.gestao.gestaowfapi.service.CustomerService;
import com.gestao.gestaowfapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<Mono<Order>> create(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(orderDTO));
    }

}
