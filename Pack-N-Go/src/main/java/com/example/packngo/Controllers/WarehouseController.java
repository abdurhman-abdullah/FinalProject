package com.example.packngo.Controllers;

import com.example.packngo.DTOs.Warehouse.WarehouseDTO;
import com.example.packngo.Models.MyUser;
import com.example.packngo.Services.WarehouseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/warehouse")
@AllArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(200).body(warehouseService.getAll(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity add(@AuthenticationPrincipal MyUser user, @RequestBody @Valid WarehouseDTO dto){
        warehouseService.add(user, dto);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@AuthenticationPrincipal MyUser user, @PathVariable int id, @RequestBody @Valid WarehouseDTO dto){
        warehouseService.update(user.getId(), id,dto);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser user, @PathVariable int id){
        warehouseService.delete(user.getId(), id);
        return ResponseEntity.status(200).body("Success");
    }

    @GetMapping("/getAllByContractor")
    public ResponseEntity getAllByContractor(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(200).body(warehouseService.getAllByContractor(user.getContractor().getId()));
    }

    @GetMapping("/{id}/contractor")
    public ResponseEntity getWarehouseByContractor(@AuthenticationPrincipal MyUser user, @PathVariable int id){
        return ResponseEntity.status(200).body(warehouseService.findByIdAndContractor_Id(user.getContractor().getId(), id));
    }

//    @PutMapping("/stock_in/{warehouseId}/order/{order_id}")
//    public ResponseEntity stockIn(@AuthenticationPrincipal MyUser userId, @PathVariable int warehouseId, @PathVariable int order_id){
//        warehouseService.stockIn(warehouseId, userId.getContractor().getId(), order_id);
//        return ResponseEntity.status(200).body("Success");
//    }
//
//    @PutMapping("/stock_out/{warehouseId}/order/{order_id}")
//    public ResponseEntity stockOut(@PathVariable int warehouseId, @AuthenticationPrincipal MyUser userId, @PathVariable int order_id){
//        warehouseService.stockOut(warehouseId, userId.getContractor().getId(), order_id);
//        return ResponseEntity.status(200).body("Success");
//    }
//
//    @PutMapping("/totalPrice/{warehouseId}/contractor/order/{order_id}")
//    public ResponseEntity totalPrice(@PathVariable int warehouseId, @AuthenticationPrincipal MyUser contractorId, @PathVariable int order_id){
//        warehouseService.totalPrice(warehouseId, contractorId.getContractor().getId(), order_id);
//        return ResponseEntity.status(200).body("Success");
//    }
}
