package com.example.packngo.Controllers;

import com.example.packngo.DTOs.Contractor.ContractorDTO;
import com.example.packngo.Models.MyUser;
import com.example.packngo.Services.ContractorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contractor")
@AllArgsConstructor
public class ContractorController {

    private final ContractorService contractorService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(200).body(contractorService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@AuthenticationPrincipal MyUser user, @RequestBody @Valid ContractorDTO dto){
        contractorService.add(user.getId(), dto);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@AuthenticationPrincipal MyUser user, @PathVariable  int id, @RequestBody @Valid ContractorDTO dto){
        contractorService.update(user.getId(), id, dto);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser user, @PathVariable int id){
        contractorService.delete(user.getId(), id);
        return ResponseEntity.status(200).body("Success");
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@AuthenticationPrincipal MyUser user, @PathVariable int id){
        return ResponseEntity.status(200).body(contractorService.getById(user.getId(), id));
    }

    @GetMapping("/order/{status}")
    public ResponseEntity getOrdersByStatusAndContractorId(@AuthenticationPrincipal MyUser user, @PathVariable String status){
        return ResponseEntity.status(200).body(contractorService.findAllByContractorIdAndStatus(user.getId(), status));
    }

    @GetMapping("/getAllVehicleByContractor")
    public ResponseEntity getAllVehicleByContractor(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(200).body(contractorService.getAllVehicleByContractor(user.getId()));
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity getAllVehicleByContractor(@AuthenticationPrincipal MyUser user, @PathVariable int vehicleId ){
        return ResponseEntity.status(200).body(contractorService.findByContractor_IdAndId(user.getId(), vehicleId));
    }

    @PutMapping("/orderDirect/{orderId}/{status}")
    public ResponseEntity statusDirect(@AuthenticationPrincipal MyUser user, @PathVariable int orderId, @PathVariable String status){
        contractorService.statusDirect(user.getId(), orderId, status);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/orderWarehouse/{orderId}/{status}")
    public ResponseEntity statusWarehouse(@AuthenticationPrincipal MyUser user, @PathVariable int orderId, @PathVariable String status){
        contractorService.statusWarehouse(user.getId(), orderId, status);
        return ResponseEntity.status(200).body("Success");
    }

}
