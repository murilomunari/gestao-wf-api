package com.gestao.gestaowfapi.dto;

import java.math.BigDecimal;

public record ProductDTO(String acronym, String name, BigDecimal currentPrice) {
}
