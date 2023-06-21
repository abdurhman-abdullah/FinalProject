package com.example.packngo.Services;

import com.example.packngo.DTOs.Contractor.ContractorDTO;
import com.example.packngo.Exceptions.ApiException;
import com.example.packngo.Models.Contractor;
import com.example.packngo.Models.Order;
import com.example.packngo.Models.MyUser;
import com.example.packngo.Models.Vehicle;
import com.example.packngo.Repositories.ContractorRepository;
import com.example.packngo.Repositories.MyUserRepository;
import com.example.packngo.Repositories.OrderRepository;
import com.example.packngo.Repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
public class ContractorService {

    private final ContractorRepository contractorRepository;

    private final OrderRepository orderRepository;

    private final VehicleRepository vehicleRepository;

    private final MyUserRepository myUserRepository;

    private final ModelMapper mapper;

    public List<Contractor> getAll(){
        return contractorRepository.findAll();
    }

    public void add(int userId, ContractorDTO dto){
        MyUser myUser= myUserRepository.findMyUserById(userId);
        Contractor contractor = mapper.map(dto,Contractor.class);
        contractor.setUser(myUser);
        contractorRepository.save(contractor);
    }

    public void update(int userId, int id, ContractorDTO dto){

        MyUser myUser = myUserRepository.findMyUserById(userId);

        Contractor contractorFind = contractorRepository.findById(id).
                orElseThrow(()-> new ApiException("id not found"));

        if(contractorFind.getId() != myUser.getId())
            throw new ApiException("Error, unAuthorize");

        contractorFind.setName(dto.getName());
        contractorFind.setCommercialRecord(contractorFind.getCommercialRecord());
        contractorFind.setLocation(dto.getLocation());
        contractorFind.setPhoneNumber(dto.getPhoneNumber());
        contractorFind.setEmail(dto.getEmail());

        contractorRepository.save(contractorFind);
    }

    public void delete(int userId, int id){
        MyUser myUser = myUserRepository.findMyUserById(userId);

        Contractor contractorFind = contractorRepository.findById(id).
                orElseThrow(()-> new ApiException("id not found"));

        if(contractorFind.getId() != myUser.getId())
            throw new ApiException("Error, unAuthorize");

        contractorRepository.delete(contractorFind);
    }

    public Contractor getById(int userId, int id){
        MyUser myUser = myUserRepository.findMyUserById(userId);

        Contractor contractorFind = contractorRepository.findById(id).
                orElseThrow(()-> new ApiException("contractor not found"));

        if(contractorFind.getId() != myUser.getId())
            throw new ApiException("Error, unAuthorize");

        return contractorFind;
    }

    public List<Order> findAllByContractorIdAndStatus(int contractor_Id, String status){
        List<Order> orders = orderRepository.findAllByContractor_IdAndStatus(contractor_Id, status);

        if(orders.size() == 0)
            throw new ApiException("order status not available " + status);

        return orders;
    }

    public void statusDirect(int userId, int orderId, String status){
        MyUser myUser = myUserRepository.findMyUserById(userId);
        Order findOrder = orderRepository.findByContractor_IdAndId(userId, orderId);
        if(findOrder == null)
            throw new ApiException("order not found");

        if(findOrder.getContractor().getId() != myUser.getId())
            throw new ApiException("Error, unAuthorize");

        getAllVehicleByContractor(myUser.getContractor().getId());

        if(!findOrder.getStatus().equals("Request_Review"))
            throw new ApiException("status accept or reject");

        if(!findOrder.getType().equals("Direct"))
            throw new ApiException("order type not Direct");

         findOrder.setStatus(StringUtils.capitalize(status));
         orderRepository.save(findOrder);
    }

    public void statusWarehouse(int userId, int orderId, String status){
        MyUser myUser = myUserRepository.findMyUserById(userId);
        Order findOrder = orderRepository.findByContractor_IdAndId(userId, orderId);

        if(findOrder == null)
            throw new ApiException("order not found");

        if(findOrder.getContractor().getId() != myUser.getId())
            throw new ApiException("Error, unAuthorize");

        if(!findOrder.getStatus().equals("Request_Review"))
            throw new ApiException("status accept or reject");

        if(!findOrder.getType().equals("Warehouse"))
            throw new ApiException("order type not Direct");

        if(findOrder.getContractor().getWarehouses().size() == 0)
            throw new ApiException("contractor haven't warehouses");

        findOrder.setStatus(StringUtils.capitalize(status));
        orderRepository.save(findOrder);
    }

    public List<Vehicle> getAllVehicleByContractor(int contractor_Id){
        List<Vehicle> vehicle = vehicleRepository.findAllByContractor_Id(contractor_Id);
        if(vehicle.size() == 0)
            throw new ApiException("vehicle not found");
        return vehicle;
    }

    public Vehicle findByContractor_IdAndId(int userId, int vehicleId){
        MyUser myUser = myUserRepository.findMyUserById(userId);
        Vehicle findVehicle = vehicleRepository.findByContractor_IdAndId(myUser.getId(), vehicleId);

        if(findVehicle == null)
            throw new ApiException("vehicle not found");

        if(findVehicle.getContractor().getId() != myUser.getId())
            throw new ApiException("Error, unAuthorize");

        return findVehicle;
    }
}
