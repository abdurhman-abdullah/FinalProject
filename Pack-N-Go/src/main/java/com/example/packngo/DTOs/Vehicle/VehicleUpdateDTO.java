package com.example.packngo.DTOs.Vehicle;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleUpdateDTO {

    @NotEmpty(message = "vehicleType must be not empty")
    private String vehicleType;

    @NotNull(message = "vehicleCapacity must be not null")
    private Integer vehicleCapacity;

    @NotNull(message = "pricePerPerson must be not null")
    private Integer pricePerPerson;

    private Integer quantityDiscount;
}
