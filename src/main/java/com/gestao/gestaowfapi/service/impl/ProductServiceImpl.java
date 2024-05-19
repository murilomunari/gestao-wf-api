package com.gestao.gestaowfapi.service.impl;

import com.gestao.gestaowfapi.dto.ProductDTO;
import com.gestao.gestaowfapi.mapper.ProductMapper;
import com.gestao.gestaowfapi.model.Product;
import com.gestao.gestaowfapi.repository.ProductRepository;
import com.gestao.gestaowfapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ReactiveMongoTemplate mongoTemplate;

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

    @Override
    public Flux<Product> findAllByName(String name, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Query query = new Query().with(pageable);
        query.addCriteria(Criteria.where("name").is(name));

        return mongoTemplate.find(query, Product.class);
    }

    @Override
    public Flux<Product> findByParam(String acronym, String name, String currentPrice, int pageNumber, int pageSize) {
        Criteria criteria = new Criteria();

        if (!Objects.equals(acronym, "")) {
            criteria.and("acronym").is(acronym);
        }
        if (!Objects.equals(name, "")) {
            criteria.and("name").regex("^" + name, "i");
        }
        if (!Objects.equals(currentPrice, "")) {
            criteria.and("currentPrice").lte(Double.parseDouble(currentPrice));
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Query query = new Query().with(pageable);
        query.addCriteria(criteria);

        return mongoTemplate.find(query, Product.class);
    }
}
