package com.example.packngo.Repositories;
import com.example.packngo.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByCustomer_Id(int customer_Id);

    List<Order> findAllByContractor_IdAndStatus(int contractor_Id, String status);

    Order findByContractor_IdAndId(int contractor_Id, int order_id);

}
