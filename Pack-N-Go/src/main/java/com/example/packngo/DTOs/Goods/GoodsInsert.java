package com.example.packngo.DTOs.Goods;

import com.example.packngo.Models.Goods;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoodsInsert {
    @NotNull(message = "orderId must be not null")
    private Integer orderId;

    @NotNull(message = "warehouse must be not null")
    private int warehouse;
}
