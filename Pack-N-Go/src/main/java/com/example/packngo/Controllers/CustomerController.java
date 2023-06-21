package com.example.packngo.Controllers;

import com.example.packngo.DTOs.Customer.CustomerDTO;
import com.example.packngo.Models.MyUser;
import com.example.packngo.Services.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(customerService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@AuthenticationPrincipal MyUser userId, @RequestBody @Valid CustomerDTO dto){
        customerService.add(userId.getId(), dto);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@AuthenticationPrincipal MyUser userId, @PathVariable int id, @RequestBody @Valid CustomerDTO dto){
        customerService.update(userId.getId(), id,dto);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser userId, @PathVariable int id){
        customerService.delete(userId.getId(), id);
        return ResponseEntity.status(200).body("Success");
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@AuthenticationPrincipal MyUser userId, @PathVariable int id){
        return ResponseEntity.status(200).body(customerService.getById(userId.getId(), id));
    }

}
