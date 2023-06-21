package com.example.packngo.Repositories;

import com.example.packngo.Models.Direct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectRepository extends JpaRepository<Direct, Integer> {
    Direct findByVehicle_Id(int vehicleId);
}
