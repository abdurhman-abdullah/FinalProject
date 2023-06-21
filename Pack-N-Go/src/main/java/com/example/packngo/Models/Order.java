package com.example.packngo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customer_Order")
public class Order {
    @Id
    @GeneratedValue(generator = "id_Oc_Seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_Oc_Seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "orderOfType must be not empty")
    @Column(columnDefinition = "varchar(20) check(type = 'Direct' or type = 'Warehouse')")
    private String type;

    @NotNull(message = "quantity must be not null")
    @Column(columnDefinition = "int")
    private Integer quantity;

    @NotEmpty(message = "description must be not empty")
    @Column(columnDefinition = "varchar(300) not null")
    private String description;

    @NotEmpty(message = "fromLocation must be not empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String fromLocation;

    @NotEmpty(message = "toLocation must be not empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String toLocation;

    @NotNull(message = "dateDay must be not null")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @FutureOrPresent
    @Column(columnDefinition = "date")
    private Date dateDay;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @FutureOrPresent
    @Column(columnDefinition = "date")
    private LocalDate dateArrived;

    @Column(columnDefinition = "float")
    private Double totalPrice;

    @NotEmpty(message = "status must be not empty")
    @Column(columnDefinition = "varchar(20) check(status = 'Request_Review' or status = 'Accept' or status = 'Reject')")
    private String status;

    @Column(columnDefinition = "date")
    private LocalDate createdAt;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JsonIgnore
    private Contractor contractor;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    @PrimaryKeyJoinColumn
    private Review review;

}
