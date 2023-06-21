package com.example.packngo.Repositories;

import com.example.packngo.Models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Warehouse findByIdAndContractor_Id(int warehouseId, int contractor_Id);
    List<Warehouse> findAllByContractor_Id(int contractor_Id);
}
