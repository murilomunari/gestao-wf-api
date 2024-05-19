package com.gestao.gestaowfapi.repository;

import com.gestao.gestaowfapi.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.print.Pageable;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Mono<Product> findByAcronym(String acronym);


    @Query("{name:{$regex:\'^ca\', $options:\'i\'}}")
    Flux<Product> findAllByName(@Param("name") String name, Pageable pageable);
}
