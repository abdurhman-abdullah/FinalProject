package com.example.packngo.Services;

import com.example.packngo.DTOs.Warehouse.WarehouseDTO;
import com.example.packngo.Exceptions.ApiException;
import com.example.packngo.Models.Contractor;
import com.example.packngo.Models.MyUser;
import com.example.packngo.Models.Warehouse;
import com.example.packngo.Repositories.ContractorRepository;
import com.example.packngo.Repositories.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final ContractorRepository contractorRepository;

    private final ModelMapper mapper;

    public List<Warehouse> getAll(int contractorId){
        return warehouseRepository.findAllByContractor_Id(contractorId);
    }

    public void add(MyUser userId, WarehouseDTO dto){
        Contractor contractor = contractorRepository.findById(userId.getId())
                .orElseThrow(() -> new ApiException("contractor not found"));
        Warehouse warehouse = mapper.map(dto, Warehouse.class);
        warehouse.setContractor(contractor);
        warehouse.setExistingPayload(0);
        warehouse.setRemainingCapacity(100);
        warehouseRepository.save(warehouse);
    }

    public void update(int userId, int id, WarehouseDTO dto){
        Warehouse findWarehouse = warehouseRepository.findById(id).
                orElseThrow(()-> new ApiException("warehouse not found"));


        if(!findWarehouse.getContractor().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        Warehouse warehouse = mapper.map(dto, findWarehouse.getClass());
        warehouse.setId(findWarehouse.getId());
        warehouse.setContractor(findWarehouse.getContractor());
        warehouse.setExistingPayload(findWarehouse.getExistingPayload());
        warehouse.setRemainingCapacity(findWarehouse.getRemainingCapacity());

        warehouseRepository.save(warehouse);
    }

    public void delete(int userId, int id){
        Warehouse findWarehouse = warehouseRepository.findById(id).
                orElseThrow(()-> new ApiException("id not found"));

        if(!findWarehouse.getContractor().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        if(findWarehouse.getRemainingCapacity() != 0)
            new ApiException("warehouse cannot deleted has goods");

        warehouseRepository.delete(findWarehouse);
    }

    public Warehouse findByIdAndContractor_Id(int contractorId, int id){
        Warehouse warehouse = warehouseRepository.findByIdAndContractor_Id(id, contractorId);

        if(warehouse == null)
            throw new ApiException("warehouse not found");

        return warehouse;
    }

    public List<Warehouse> getAllByContractor(int contractorId){
        List<Warehouse> warehouse = warehouseRepository.findAllByContractor_Id(contractorId);

        if(warehouse == null)
            throw new ApiException("warehouse not found");

        return warehouse;
    }
}
