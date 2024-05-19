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

    @GetMapping("acronym/{acronym}")
    public ResponseEntity<Mono<Product>> findByAcronym(@PathVariable("acronym") String acronym) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByAcronym(acronym));

    }

    @GetMapping("name/{name}")
    public ResponseEntity<Flux<Product>> findAllByName(@PathVariable("name") String name,
                                                       @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
                                                       @RequestParam(value = "name", required = false, defaultValue = "5") int pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllByName(name,pageNumber, pageSize));
    }

    @GetMapping("/params")
    public ResponseEntity<Flux<Product>> findAllByParams(@RequestParam(value = "acronym", required = false, defaultValue = "") String acronym,
                                                         @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                         @RequestParam(value = "currentPrice", required = false, defaultValue = "") String currentPrice,
                                                         @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
                                                         @RequestParam(value = "name", required = false, defaultValue = "5") int pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByParam(acronym, name, currentPrice, pageNumber, pageSize));
    }
}
