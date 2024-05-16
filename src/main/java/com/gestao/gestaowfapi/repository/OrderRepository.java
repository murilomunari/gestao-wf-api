package com.gestao.gestaowfapi.repository;

import com.gestao.gestaowfapi.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
