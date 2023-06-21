package com.example.packngo.Services;

import com.example.packngo.DTOs.Goods.GoodsInsert;
import com.example.packngo.Exceptions.ApiException;
import com.example.packngo.Models.*;
import com.example.packngo.Repositories.GoodsRepository;
import com.example.packngo.Repositories.OrderRepository;
import com.example.packngo.Repositories.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@AllArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final WarehouseRepository warehouseRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    public List<Goods> getAll(int contractorId){
        return goodsRepository.findAllByWarehouses_Contractor_Id(contractorId);
    }

    public void stockIn(MyUser userId, GoodsInsert dto){
        Warehouse warehouse = warehouseRepository.findByIdAndContractor_Id(dto.getWarehouse(), userId.getId());
        Order order = orderRepository.findByContractor_IdAndId(userId.getId(), dto.getOrderId());
        if(warehouse == null)
            throw new ApiException("warehouse not found");

        if(order == null || !order.getStatus().equals("Accept"))
            throw new ApiException("order not accept");

        if(!order.getType().equals("Warehouse"))
            throw new ApiException("order type not Warehouse");

        if(warehouse.getWarehouseCapacity() < warehouse.getRemainingCapacity()) {
            warehouse.setIsFull("Full");
            warehouseRepository.save(warehouse);
        }

        Goods goods = mapper.map(dto, Goods.class);

        int fullWarehouse = order.getQuantity() + warehouse.getExistingPayload();

        int remainingCapacity = warehouse.getWarehouseCapacity() - fullWarehouse;

        goods.setStockIn(LocalDate.now());
        warehouse.setExistingPayload(fullWarehouse);
        warehouse.setRemainingCapacity(remainingCapacity);
        goods.setOrderId(order.getId());
        goods.setWarehouses(warehouse);
        goodsRepository.save(goods);
        warehouseRepository.save(warehouse);
    }

    public void stockOut(int userId, GoodsInsert dto){
        Warehouse warehouse = warehouseRepository.findByIdAndContractor_Id(dto.getWarehouse(), userId);
        Order order = orderRepository.findByContractor_IdAndId(userId, dto.getOrderId());
        Goods goods = goodsRepository.findByOrderId(order.getId());

        if(warehouse == null)
            throw new ApiException("warehouse not found");

        if(order == null || (!order.getStatus().equals("Accept")))
            throw new ApiException("order not found");

        if(goods == null)
            throw new ApiException("order not in warehouse");

        int existingPayload =  warehouse.getExistingPayload() - order.getQuantity();

        int remainingCapacity = warehouse.getWarehouseCapacity() - existingPayload;

        goods.setStockOut(LocalDate.now());
        warehouse.setIsFull("not filled");
        warehouse.setExistingPayload(existingPayload);
        warehouse.setRemainingCapacity(remainingCapacity);
        warehouseRepository.save(warehouse);
    }

    public void totalPrice(int userId, GoodsInsert dto){
        Warehouse warehouse = warehouseRepository.findByIdAndContractor_Id(dto.getWarehouse(), userId);
        Order order = orderRepository.findByContractor_IdAndId(userId, dto.getOrderId());
        Goods goods = goodsRepository.findByOrderId(order.getId());

        int days = goods.getStockIn().getDayOfMonth() - goods.getStockOut().getDayOfMonth();

        double totalPrice = warehouse.getPriceOfDay() * days;

        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
    }

    public void delete(int userId, int id){
        Warehouse findWarehouse = warehouseRepository.findById(id).
                orElseThrow(()-> new ApiException("id not found"));

        if(!findWarehouse.getContractor().getId().equals(userId))
            throw new ApiException("Error, unAuthorize");

        warehouseRepository.delete(findWarehouse);
    }

}
