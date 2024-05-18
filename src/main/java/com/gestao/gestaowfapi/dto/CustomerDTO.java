package com.gestao.gestaowfapi.dto;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

public record CustomerDTO(@Email  String email, String firstName, String lastName, @CPF String cpf) {
}
