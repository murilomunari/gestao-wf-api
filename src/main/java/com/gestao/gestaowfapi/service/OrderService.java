package com.gestao.gestaowfapi.service;

import com.gestao.gestaowfapi.dto.OrderDTO;
import com.gestao.gestaowfapi.model.Order;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<Order> create(OrderDTO dto);

    Mono<Order> findById(String id);
}
