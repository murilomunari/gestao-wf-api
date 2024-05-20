package com.gestao.gestaowfapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document("orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Order {

    @Id
    private String id;

    private BigDecimal originalPrice;

    private BigDecimal discount;

    @Builder.Default
    private LocalDateTime dtRegistedOrder = LocalDateTime.now();

    @DBRef
    private Customer customerId;

    @DBRef
    private Product productId;
}
