package com.example.packngo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MyUser")
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(generator = "id_Usr_Seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id", sequenceName = "id_Usr_Seq", initialValue = 1, allocationSize = 1)
    private Integer id;

    @NotEmpty(message = "userName must be not empty")
    @Column(columnDefinition = "varchar(30) unique")
    private String username;

    @NotEmpty(message = "password must be not empty")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{8,}$", message = "password must have characters and digits")
    @Column(columnDefinition = "varchar(100) not null")
    private String password;

    @NotEmpty(message = "role must be not empty")
    @Column(columnDefinition = "varchar(20) check(role = 'CUSTOMER' or role = 'CONTRACTOR')")
    private String role;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Contractor contractor;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
