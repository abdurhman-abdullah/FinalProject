package com.example.packngo.Controllers;

import com.example.packngo.DTOs.Vehicle.VehicleDTO;
import com.example.packngo.DTOs.Vehicle.VehicleUpdateDTO;
import com.example.packngo.Models.MyUser;
import com.example.packngo.Services.VehicleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(vehicleService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@AuthenticationPrincipal MyUser user, @RequestBody @Valid VehicleDTO dto){
        vehicleService.add(user, dto);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@AuthenticationPrincipal MyUser userId, @PathVariable int id, @RequestBody @Valid VehicleUpdateDTO dto){
        vehicleService.update(userId, id,dto);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser userId, @PathVariable int id){
        vehicleService.delete(userId, id);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/totalPrice/{vehicleId}/order/{orderId}")
    public ResponseEntity totalPrice(@AuthenticationPrincipal MyUser userId, @PathVariable int vehicleId, @PathVariable int orderId){
        vehicleService.totalPrice(userId, vehicleId, orderId);
        return ResponseEntity.status(200).body("Success");
    }
}
