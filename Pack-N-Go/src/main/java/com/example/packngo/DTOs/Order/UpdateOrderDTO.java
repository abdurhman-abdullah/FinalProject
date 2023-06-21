package com.example.packngo.DTOs.Order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UpdateOrderDTO {

    private Integer id;

    @NotNull(message = "quantity must be not null")
    private Integer quantity;

    @NotEmpty(message = "description must be not empty")
    private String description;

    @NotEmpty(message = "fromLocation must be not empty")
    private String fromLocation;

    @NotEmpty(message = "toLocation must be not empty")
    private String toLocation;

    private Date dateDay;
}
