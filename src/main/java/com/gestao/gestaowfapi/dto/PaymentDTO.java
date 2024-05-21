package com.gestao.gestaowfapi.dto;

public record PaymentDTO(

        CreditCardDTO creditCard,
        String customerId,
        String orderId


){
}
