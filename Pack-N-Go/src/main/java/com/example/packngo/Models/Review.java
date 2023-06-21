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
public class Review {
    @Id
    private Integer id;

    @NotEmpty(message = "description must be not empty")
    @Column(columnDefinition = "varchar(300) not null")
    private String review;

    @NotNull(message = "stars must be not null")
    @Column(columnDefinition = "int check(stars <= 5) ")
    private Integer stars;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Order order;
}
