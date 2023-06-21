package com.example.packngo.Repositories;

import com.example.packngo.Models.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Integer> {
//    Contractor findContractorByUser_Username(String name);
}
