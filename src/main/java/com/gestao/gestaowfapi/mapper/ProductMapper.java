package com.gestao.gestaowfapi.mapper;

import com.gestao.gestaowfapi.dto.ProductDTO;
import com.gestao.gestaowfapi.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dtCreation", ignore = true)
    Product toModel(ProductDTO dto);
}
