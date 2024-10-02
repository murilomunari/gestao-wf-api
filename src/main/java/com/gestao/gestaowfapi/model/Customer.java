package com.gestao.gestaowfapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // Marca a classe como uma entidade JPA
@Table(name = "customers") // Define o nome da tabela no banco de dados
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID
    private Long id;

    @Email
    @Column(unique = true) // Define a coluna como única no banco de dados
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @CPF
    @NotBlank
    @Column(unique = true) // Define a coluna como única no banco de dados
    private String cpf;
}
