package com.example.packngo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Direct {
    @Id
    private Integer id;

    @Column(columnDefinition = "int")
    private Integer minimumQuantityRequired;

    @NotEmpty(message = "driverName must be not empty")
    @Column(columnDefinition = "varchar(50) unique")
    private String driverName;

    @Column(columnDefinition = "int default 0")
    private Integer payloadNumber;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Vehicle vehicle;


}
