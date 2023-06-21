package com.example.packngo.Repositories;

import com.example.packngo.Models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Vehicle findByContractor_IdAndId(int contractor_Id, int id);

    List<Vehicle> findAllByContractor_Id(int contractor_Id);

}
