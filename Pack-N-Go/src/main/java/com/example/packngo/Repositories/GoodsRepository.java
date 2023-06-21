package com.example.packngo.Repositories;

import com.example.packngo.Models.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    List<Goods> findAllByWarehouses_Contractor_Id(int contractor_Id);

    Goods findByOrderId(int orderId);
}
