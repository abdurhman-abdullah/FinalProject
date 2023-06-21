package com.example.packngo.DTOs.Direct;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class DirectDTO {
    @NotEmpty(message = "driverName must be not empty")
    private String driverName;

    @NotNull(message = "dateOfEntry must be not null")
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dateArrival;

    @NotNull(message = "vehicle_id must be not null")
    private int vehicle_id;
}
