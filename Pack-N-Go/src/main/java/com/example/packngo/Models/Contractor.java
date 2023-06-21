package com.example.packngo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
public class Contractor {

    @Id
    private Integer id;

    @NotEmpty(message = "name must be not empty")
    @Column(columnDefinition = "varchar(50) unique")
    private String name;

    @NotEmpty(message = "commercialRecord must be not empty")
    @Column(columnDefinition = "varchar(50) unique")
    private String commercialRecord;

    @NotEmpty(message = "phoneNumber must be not empty")
    @Column(columnDefinition = "varchar(20) unique")
    private String phoneNumber;

    @NotEmpty(message = "email must be not empty")
    @Email(message = "email must be valid")
    @Column(columnDefinition = "varchar(100) unique")
    private String email;

    @NotEmpty(message = "location must be not empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String location;

    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contractor")
    private Set<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contractor")
    private Set<Vehicle> vehicles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contractor")
    private Set<Warehouse> warehouses;
}
