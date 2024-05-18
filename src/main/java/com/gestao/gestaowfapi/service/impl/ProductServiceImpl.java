package com.gestao.gestaowfapi.service.impl;

import com.gestao.gestaowfapi.dto.ProductDTO;
import com.gestao.gestaowfapi.mapper.ProductMapper;
import com.gestao.gestaowfapi.model.Product;
import com.gestao.gestaowfapi.repository.ProductRepository;
import com.gestao.gestaowfapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public Mono<Product> create(ProductDTO dto) {
        return productRepository.save(productMapper.toModel(dto));
    }

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> findByAcronym(String acronym) {
        return productRepository.findByAcronym(acronym);
    }
}
