package com.gestao.gestaowfapi.service;

import com.gestao.gestaowfapi.dto.CreditCardDTO;
import com.gestao.gestaowfapi.model.CreditCard;
import com.gestao.gestaowfapi.model.Customer;
import reactor.core.publisher.Mono;

public interface CreditCardService {

    Mono<CreditCard> findByNumber(String number);

    Mono<CreditCard> create(CreditCardDTO creditCardDto, Customer customer);

}
