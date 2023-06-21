package com.example.packngo.DTOs.Warehouse;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WarehouseDTO {

    private int id;

    @NotNull(message = "name must be not null")
    private String name;

    @NotNull(message = "lessDuration must be not null")
    private Integer lessDuration;

    @NotNull(message = "priceOfDay must be not null")
    private Integer priceOfDay;

    @NotNull(message = "warehouseCapacity must be not null")
    private Integer warehouseCapacity;
}
