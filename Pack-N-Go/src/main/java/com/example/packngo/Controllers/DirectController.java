package com.example.packngo.Controllers;

import com.example.packngo.DTOs.Direct.DirectDTO;
import com.example.packngo.Services.DirectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/direct")
@AllArgsConstructor
public class DirectController {

    private final DirectService directService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(directService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid DirectDTO dto){
        directService.add(dto);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody @Valid DirectDTO direct){
        directService.update(id,direct);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        directService.delete(id);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/payloadNumber/{vehicleId}/contractor/{contractorId}/order/{order_id}")
    public ResponseEntity payloadNumber(@PathVariable int vehicleId, @PathVariable int contractorId, @PathVariable int order_id){
        directService.payloadNumber(vehicleId, contractorId, order_id);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/contractor/{contractorId}/vehicle/{vehicleId}")
    public ResponseEntity dateArrived(@PathVariable int contractorId, @PathVariable int vehicleId){
        directService.dateArrived(contractorId, vehicleId);
        return ResponseEntity.status(200).body("Success");
    }
}
