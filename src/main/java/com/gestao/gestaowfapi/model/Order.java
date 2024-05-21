package com.gestao.gestaowfapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

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

    private LocalDateTime dtRegistedOrder;

    private String customerId;

    private String productId;
}
