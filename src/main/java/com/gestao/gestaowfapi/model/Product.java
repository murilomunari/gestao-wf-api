package com.gestao.gestaowfapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    @Size(min = 6, max = 20)
    private String acronym;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(name = "current_price", nullable = false)
    private BigDecimal currentPrice;

    @Column(name = "dt_creation", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime dtCreation = LocalDateTime.now();
}
