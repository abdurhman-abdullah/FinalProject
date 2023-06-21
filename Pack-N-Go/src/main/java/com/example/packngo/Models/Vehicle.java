package com.example.packngo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(generator = "id_Veh_Seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_Veh_Seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "description must be not empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String vehicleType;

    @NotNull(message = "vehicleCapacity must be not null")
    @Column(columnDefinition = "int")
    private Integer vehicleCapacity;

    @Column(columnDefinition = "float")
    private Double numberOfTrips;

    @NotNull(message = "pricePerPerson must be not null")
    @Column(columnDefinition = "float")
    private Double pricePerPerson;

    @Column(columnDefinition = "float")
    private Double quantityDiscount;

    @ManyToOne
    @JsonIgnore
    private Contractor contractor;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "vehicle")
    @PrimaryKeyJoinColumn
    private Direct direct;

}
