package com.gestao.gestaowfapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credit_cards")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String number;

    private String cvv;

    private int year;

    @Column(name = "document_number")
    private String documentNumber;

    private int installments;

    @Column(name = "customer_id")
    private String customerId;
}
