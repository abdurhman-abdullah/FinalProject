package com.example.packngo.Services;

import com.example.packngo.DTOs.Vehicle.VehicleDTO;
import com.example.packngo.DTOs.Vehicle.VehicleUpdateDTO;
import com.example.packngo.Exceptions.ApiException;
import com.example.packngo.Models.Contractor;
import com.example.packngo.Models.MyUser;
import com.example.packngo.Models.Order;
import com.example.packngo.Models.Vehicle;
import com.example.packngo.Repositories.ContractorRepository;
import com.example.packngo.Repositories.OrderRepository;
import com.example.packngo.Repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ContractorRepository contractorRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    public List<Vehicle> getAll(){
        return vehicleRepository.findAll();
    }

    public void add(MyUser userId , VehicleDTO dto){
        Contractor contractor = contractorRepository.findById(userId.getId())
                .orElseThrow(() -> new ApiException("contractor not found"));

        Vehicle vehicle = mapper.map(dto, Vehicle.class);
        vehicle.setContractor(contractor);

        vehicleRepository.save(vehicle);
    }

    public void update(MyUser userId, int id, VehicleUpdateDTO dto){
        Vehicle vehicleFind = vehicleRepository.findById(id).
                orElseThrow(()-> new ApiException("vehicle not found"));

        if(!vehicleFind.getContractor().getId().equals(userId.getContractor().getId()))
            throw new ApiException("Error, unAuthorize");

       Vehicle vehicle = mapper.map(dto, vehicleFind.getClass());
        vehicle.setQuantityDiscount(vehicleFind.getQuantityDiscount());
        vehicle.setId(vehicleFind.getId());
        vehicle.setContractor(vehicleFind.getContractor());

        vehicleRepository.save(vehicle);
    }

    public void delete(MyUser userId, int id){
        Vehicle vehicleFind = vehicleRepository.findById(id).
                orElseThrow(()-> new ApiException("vehicle not found"));

        if(!vehicleFind.getContractor().getId().equals(userId.getContractor().getId()))
            throw new ApiException("Error, unAuthorize");

        vehicleRepository.delete(vehicleFind);
    }

    public void totalPrice(MyUser user, int vehicleId, int orderId){
        Order order = orderRepository.findByContractor_IdAndId(user.getContractor().getId(), orderId);

        Vehicle vehicle = vehicleRepository.findByContractor_IdAndId(user.getContractor().getId(), vehicleId);

        if(!order.getType().equals("Direct"))
            throw new ApiException("order type not Direct");

        double price = 0;

        if(order.getQuantity() <= 3) {
            price = vehicle.getPricePerPerson() / order.getQuantity();
        }else {
            double d = vehicle.getQuantityDiscount() / 100;
            double discount = d * vehicle.getPricePerPerson();
            price = discount * order.getQuantity();
        }
        order.setTotalPrice(price);
        orderRepository.save(order);
    }


}
