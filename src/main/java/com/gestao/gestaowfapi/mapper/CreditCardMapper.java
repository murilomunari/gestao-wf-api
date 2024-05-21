package com.gestao.gestaowfapi.mapper;

import com.gestao.gestaowfapi.dto.CreditCardDTO;
import com.gestao.gestaowfapi.model.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface CreditCardMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    CreditCard toModel(CreditCardDTO dto);
}
