package com.example.packngo.Services;

import com.example.packngo.DTOs.Customer.CustomerDTO;
import com.example.packngo.Exceptions.ApiException;
import com.example.packngo.Models.Customer;
import com.example.packngo.Models.MyUser;
import com.example.packngo.Repositories.CustomerRepository;
import com.example.packngo.Repositories.MyUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final MyUserRepository myUserRepository;
    private final ModelMapper mapper;

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public void add(int userId, CustomerDTO dto){
        MyUser myUser = myUserRepository.findMyUserById(userId);
        Customer customer = mapper.map(dto, Customer.class);
        customer.setUser(myUser);
        customerRepository.save(customer);
    }

    public void update(int userId,int id, CustomerDTO dto){
        Customer customerFind = customerRepository.findById(id).
                orElseThrow(()-> new ApiException("customer not found"));

        if(!customerFind.getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        customerFind.setName(dto.getName());
        customerFind.setPhoneNumber(dto.getPhoneNumber());
        customerFind.setEmail(dto.getEmail());

        customerRepository.save(customerFind);
    }

    public void delete(int userId, int id){
        Customer customerFind = customerRepository.findById(id).
                orElseThrow(()-> new ApiException("customer not found"));

        if(!customerFind.getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        customerRepository.delete(customerFind);
    }

    public Customer getById(int userId,int id){
        Customer findCustomer= customerRepository.findById(id).
                orElseThrow(()-> new ApiException("customer not found"));

        if(!findCustomer.getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        return findCustomer;
    }

}
