package com.antunes.picpayfinanceservice.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo não pode ser vazio!")
    @Min(value = 4, message = "O campo deve conter entre 4 e 10 caracteres!")
    @Max(value = 10, message = "O campo deve conter entre 4 e 10 caracteres!")
    private String firstName;

    @NotEmpty(message = "O campo não pode ser vazio!")
    @Min(value = 4, message = "O campo deve conter entre 4 e 20 caracteres!")
    @Max(value = 10, message = "O campo deve conter entre 4 e 20 caracteres!")
    private String lastName;

    @Column(unique = true)
    @NotEmpty(message = "O campo não pode ser vazio!")
    private String document;
    @Column(unique = true)
    @Email(message = "Email inválido!")
    @NotEmpty(message = "O campo não pode ser vazio!")
    private String email;

    @NotEmpty(message = "O campo não pode ser vazio!")
    @Min(value = 7, message = "O campo deve conter entre 7 e 20 caracteres!")
    @Max(value = 20, message = "O campo deve conter entre 7 e 20 caracteres!")
    private String password;

    @NotEmpty(message = "O campo não pode ser vazio!")
    @Min(value = 4, message = "O campo deve conter entre 4 e 20 caracteres!")
    @Max(value = 10, message = "O campo deve conter entre 4 e 20 caracteres!")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
