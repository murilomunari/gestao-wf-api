package com.gestao.gestaowfapi.service.impl;

import com.gestao.gestaowfapi.dto.CreditCardDTO;
import com.gestao.gestaowfapi.exception.NotFoundException;
import com.gestao.gestaowfapi.mapper.CreditCardMapper;
import com.gestao.gestaowfapi.model.CreditCard;
import com.gestao.gestaowfapi.model.Customer;
import com.gestao.gestaowfapi.repository.CreditCardRepository;
import com.gestao.gestaowfapi.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    private final CreditCardMapper creditCardMapper;

    @Override
    public Mono<CreditCard> findByNumber(String number) {
        return creditCardRepository.findByNumber(number).switchIfEmpty(Mono.error(()-> new NotFoundException("Credit card not found")));
    }

    @Override
    public Mono<CreditCard> create(CreditCardDTO dto, Customer customer) {

        CreditCard creditCard = creditCardMapper.toModel(dto);
        creditCard.setCustomer(customer);
        return creditCardRepository.save(creditCard);
    }
}
