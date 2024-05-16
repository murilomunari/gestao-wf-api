package com.gestao.gestaowfapi.mapper;

import com.gestao.gestaowfapi.dto.CustomerDTO;
import com.gestao.gestaowfapi.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    Customer toModel(CustomerDTO dto);
}
