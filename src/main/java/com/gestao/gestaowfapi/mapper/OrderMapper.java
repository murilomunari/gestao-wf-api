package com.gestao.gestaowfapi.mapper;

import com.gestao.gestaowfapi.dto.OrderDTO;
import com.gestao.gestaowfapi.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "originalPrice", ignore = true)
    @Mapping(target = "dtResgisterOrder", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "product", ignore = true)
    Order toModel(OrderDTO dto);
}
