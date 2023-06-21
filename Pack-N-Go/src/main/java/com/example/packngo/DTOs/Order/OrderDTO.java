package com.example.packngo.DTOs.Order;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class OrderDTO {

    private Integer id;

    @NotEmpty(message = "type must be Direct or Warehouse")
    private String type;

    @NotNull(message = "quantity must be not null")
    private Integer quantity;

    @NotEmpty(message = "description must be not empty")
    private String description;

    @NotEmpty(message = "fromLocation must be not empty")
    private String fromLocation;

    @NotEmpty(message = "toLocation must be not empty")
    private String toLocation;

    @NotNull(message = "date must be not null")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @FutureOrPresent
    private Date dateDay;

    @NotNull(message = "contractor_Id must be not found")
    private int contractor_Id;

}
