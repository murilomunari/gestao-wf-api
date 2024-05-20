package com.gestao.gestaowfapi.mapper;

import com.gestao.gestaowfapi.dto.OrderDTO;
import com.gestao.gestaowfapi.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "originalPrice", ignore = true)
    @Mapping(target = "dtRegistedOrder", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "productId", ignore = true)
    Order toModel(OrderDTO dto);
}
