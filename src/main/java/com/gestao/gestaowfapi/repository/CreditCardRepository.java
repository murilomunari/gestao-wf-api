package com.gestao.gestaowfapi.repository;

import com.gestao.gestaowfapi.model.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreditCardRepository extends MongoRepository<CreditCard, String> {
}
