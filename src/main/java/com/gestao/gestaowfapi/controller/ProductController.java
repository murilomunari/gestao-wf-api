package com.gestao.gestaowfapi.controller;

import com.gestao.gestaowfapi.dto.ProductDTO;
import com.gestao.gestaowfapi.model.Product;
import com.gestao.gestaowfapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Mono<Void>> create(@RequestBody ProductDTO productDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productDTO).then());
    }

    @GetMapping
    public ResponseEntity<Flux<Product>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/acronym")
    public ResponseEntity<Mono<Product>> findByAcronym(@PathVariable("acronym") String acronym) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByAcronym(acronym));

    }
}
