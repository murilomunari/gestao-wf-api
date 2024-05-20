package com.gestao.gestaowfapi.dto;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

public record CustomerDTO( String firstName,

                           String lastName,

                           @Email String email,

                           @CPF String cpf) {
}
