package com.example.packngo.DTOs.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    @NotEmpty(message = "name must be not empty")
    private String name;

    @NotEmpty(message = "phoneNumber must be not empty")
    private String phoneNumber;

    @NotEmpty(message = "email must be not empty")
    @Email(message = "email must be valid")
    private String email;
}
