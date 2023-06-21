package com.example.packngo.Services;

import com.example.packngo.DTOs.Order.OrderDTO;
import com.example.packngo.DTOs.Order.UpdateOrderDTO;
import com.example.packngo.Exceptions.ApiException;
import com.example.packngo.Models.*;
import com.example.packngo.Repositories.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MyUserRepository myUserRepository;
    private final ContractorRepository contractorRepository;
    private final ModelMapper mapper;

    public List<Order> getAll(int userId){
        MyUser myUser = myUserRepository.findMyUserById(userId);
        return orderRepository.findAllByCustomer_Id(myUser.getId());
    }

    public void add(int userId, OrderDTO dto){

        Order order = mapper.map(dto,Order.class);

        MyUser myUser = myUserRepository.findMyUserById(userId);

        Contractor contractor = contractorRepository.findById(dto.getContractor_Id())
                .orElseThrow(()-> new ApiException("contractor not found"));

        LocalDate dateNow = LocalDate.now();
        order.setStatus("Request_Review");
        order.setCreatedAt(dateNow);
        order.setCustomer(myUser.getCustomer());
        order.setContractor(contractor);

        orderRepository.save(order);
    }

    public void update(int userId, int id, UpdateOrderDTO dto){
        MyUser myUser = myUserRepository.findMyUserById(userId);
        Order findOrder = orderRepository.findById(id).
                orElseThrow(()-> new ApiException("order not found"));
        if(findOrder.getCustomer() != myUser.getCustomer())
            throw new ApiException("Error, unAuthorize");

        dto.setId(findOrder.getId());

        Order order = mapper.map(dto,findOrder.getClass());

        if(dto.getDateDay() == null)
            order.setDateDay(findOrder.getDateDay());
        else
            order.setDateDay(dto.getDateDay());

        order.setType(findOrder.getType());
        order.setStatus("Request_Review");
        order.setCreatedAt(findOrder.getCreatedAt());
        order.setCustomer(myUser.getCustomer());
        order.setContractor(findOrder.getContractor());
        orderRepository.save(order);
    }

    public void delete(int userId, int id){
        Order findOrder = orderRepository.findById(id).
                orElseThrow(()-> new ApiException("order not found"));

        if(!findOrder.getCustomer().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        if(findOrder.getStatus().equals("Accept"))
            throw new ApiException("cannot delete order has status accept");

        orderRepository.delete(findOrder);
    }
    public Order getById(int userId, int id){
        Order findOrder = orderRepository.findById(id)
                .orElseThrow(()-> new ApiException("order not found"));

        if(!findOrder.getCustomer().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        return findOrder;
    }

}
