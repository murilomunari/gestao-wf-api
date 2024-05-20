package com.gestao.gestaowfapi.service.impl;

import com.gestao.gestaowfapi.dto.OrderDTO;
import com.gestao.gestaowfapi.exception.BadRequestException;
import com.gestao.gestaowfapi.exception.NotFoundException;
import com.gestao.gestaowfapi.mapper.OrderMapper;
import com.gestao.gestaowfapi.model.Order;
import com.gestao.gestaowfapi.model.Product;
import com.gestao.gestaowfapi.repository.OrderRepository;
import com.gestao.gestaowfapi.service.CustomerService;
import com.gestao.gestaowfapi.service.OrderService;
import com.gestao.gestaowfapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final CustomerService customerService;

    private final ProductService productService;

    @Override
    public Mono<Order> create(OrderDTO orderDTO) {
        return customerService.findById(orderDTO.customerId())
                .flatMap(customer -> productService.findByAcronym(orderDTO.productAcronym())
                .flatMap(product -> {
                    Order order = orderMapper.toModel(orderDTO);
                    if (orderDTO.discount().intValue() > 0){
                        if (orderDTO.discount().compareTo(product.getCurrentPrice()) > 0){
                            return Mono.error(()-> new BadRequestException("Discount can not be greater than currentPrice"));
                        }
                        order.setOriginalPrice(product.getCurrentPrice().subtract(orderDTO.discount()));
                    }else {
                        order.setOriginalPrice(product.getCurrentPrice());
                    }
                    order.setProduct(product);
                    order.setCustomer(customer);
                    return orderRepository.save(order);
                }));
    }

    @Override
    public Mono<Order> findById(String id) {
        return orderRepository.findById(id).switchIfEmpty(Mono.error(()-> new NotFoundException("Order not found")));
    }
}
