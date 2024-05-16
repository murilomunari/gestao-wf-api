package com.gestao.gestaowfapi.service;

import com.gestao.gestaowfapi.dto.ProductDTO;
import com.gestao.gestaowfapi.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<Product> create(ProductDTO dto);

    Flux<Product> findAll();
}
