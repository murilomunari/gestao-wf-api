package com.gestao.gestaowfapi.repository;

import com.gestao.gestaowfapi.model.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CreditCardRepository extends ReactiveMongoRepository<CreditCard, String> {
}
