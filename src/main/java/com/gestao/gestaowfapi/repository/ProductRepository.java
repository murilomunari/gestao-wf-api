package com.gestao.gestaowfapi.repository;

import com.gestao.gestaowfapi.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Mono<Product> findByAcronym(String acronym);

}
