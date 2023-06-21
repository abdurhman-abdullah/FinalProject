package com.example.packngo.Services;

import com.example.packngo.DTOs.Direct.DirectDTO;
import com.example.packngo.Exceptions.ApiException;
import com.example.packngo.Models.Direct;
import com.example.packngo.Models.Order;
import com.example.packngo.Models.Vehicle;
import com.example.packngo.Repositories.ContractorRepository;
import com.example.packngo.Repositories.DirectRepository;
import com.example.packngo.Repositories.OrderRepository;
import com.example.packngo.Repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@AllArgsConstructor
public class DirectService {

    private final DirectRepository directRepository;
    private final VehicleRepository vehicleRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    public List<Direct> getAll(){
        return directRepository.findAll();
    }

    public void add(DirectDTO dto){
       Direct direct = mapper.map(dto, Direct.class);

        Vehicle vehicle = vehicleRepository.findById(dto.getVehicle_id())
                        .orElseThrow(()-> new ApiException("vehicle not found"));

        direct.setVehicle(vehicle);

        directRepository.save(direct);
    }

    public void update(int id, DirectDTO direct){
        Direct directFind = directRepository.findById(id).
                orElseThrow(()-> new ApiException("direct not found"));
        directFind.setDriverName(direct.getDriverName());
        directRepository.save(directFind);
    }

    public void delete(int id){
        Direct directFind = directRepository.findById(id).
                orElseThrow(()-> new ApiException("direct not found"));

        directRepository.delete(directFind);
    }

    public void payloadNumber(int vehicleId, int contractorId, int order_Id){
        Direct direct = directRepository.findByVehicle_Id(vehicleId);
        Order order = orderRepository.findByContractor_IdAndId(contractorId, order_Id);

        if(order == null && order.getStatus() != "Request_Review")
            throw new ApiException("order not found");

        int fullVehicle = order.getQuantity() + direct.getPayloadNumber();

        if(fullVehicle > direct.getVehicle().getVehicleCapacity())
            throw new ApiException("vehicle capacity is full");

        direct.setPayloadNumber(fullVehicle);
        directRepository.save(direct);
    }

    public void dateArrived(int contractorId, int vehicleId){
        Vehicle vehicle = vehicleRepository.findByContractor_IdAndId(contractorId, vehicleId);

        LocalDate dateArrived = LocalDate.now();

        for (Order item : vehicle.getContractor().getOrders()) {
            item.setDateArrived(dateArrived);
            orderRepository.save(item);
        }
    }
}
