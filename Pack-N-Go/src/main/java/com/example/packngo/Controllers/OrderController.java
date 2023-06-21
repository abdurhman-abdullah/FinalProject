package com.example.packngo.Controllers;

import com.example.packngo.DTOs.Order.OrderDTO;
import com.example.packngo.DTOs.Order.UpdateOrderDTO;
import com.example.packngo.Models.MyUser;
import com.example.packngo.Services.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(@AuthenticationPrincipal MyUser userId){
        return ResponseEntity.status(200).body(orderService.getAll(userId.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity add(@AuthenticationPrincipal MyUser userId, @RequestBody @Valid OrderDTO dto){
        orderService.add(userId.getId(), dto);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@AuthenticationPrincipal MyUser userId, @PathVariable int id, @RequestBody @Valid UpdateOrderDTO dto){
        orderService.update(userId.getId(), id, dto);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser userId, @PathVariable int id){
        orderService.delete(userId.getId(), id);
        return ResponseEntity.status(200).body("Success");
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@AuthenticationPrincipal MyUser userId, @PathVariable int id){
        return ResponseEntity.status(200).body(orderService.getById(userId.getId(), id));
    }

}
