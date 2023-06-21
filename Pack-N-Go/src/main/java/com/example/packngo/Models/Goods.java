package com.example.packngo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Goods {
    @Id
    @GeneratedValue(generator = "id_Gds_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_Gds_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotNull(message = "orderId must be not null")
    @Column(columnDefinition = "int")
    private Integer orderId;

    @Column(columnDefinition = "date")
    private LocalDate stockIn;

    @Column(columnDefinition = "date")
    private LocalDate stockOut;

    @ManyToOne
    @JsonIgnore
    private Warehouse warehouses;
}
