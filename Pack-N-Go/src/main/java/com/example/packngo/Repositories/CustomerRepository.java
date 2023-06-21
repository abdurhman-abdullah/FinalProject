package com.example.packngo.Repositories;

import com.example.packngo.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//    Customer findCustomerByUser_Username(String username);
}
