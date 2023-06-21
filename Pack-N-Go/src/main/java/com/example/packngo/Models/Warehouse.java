package com.example.packngo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Warehouse {
    @Id
    @GeneratedValue(generator = "id_War_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_War_seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotNull(message = "name must be not null")
    @Column(columnDefinition = "varchar(30)")
    private String name;

    @NotNull(message = "lessDuration must be not null")
    @Column(columnDefinition = "int")
    private Integer lessDuration;

    @NotNull(message = "priceOfDay must be not null")
    @Column(columnDefinition = "int")
    private Integer priceOfDay;

    @NotNull(message = "warehouseCapacity must be not null")
    @Column(columnDefinition = "int")
    private Integer warehouseCapacity;

    @Column(columnDefinition = "int")
    private Integer existingPayload;

    @Column(columnDefinition = "varchar(20)")
    private String isFull;

    @Column(columnDefinition = "int")
    private Integer remainingCapacity;

    @ManyToOne
    @JsonIgnore
    private Contractor contractor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouses")
    private Set<Goods> warehouses;


}
