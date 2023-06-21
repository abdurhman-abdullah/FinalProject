package com.example.packngo.Controllers;

import com.example.packngo.DTOs.Goods.GoodsInsert;
import com.example.packngo.Models.MyUser;
import com.example.packngo.Services.GoodsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/goods")
@AllArgsConstructor
public class GoodsController {
    private final GoodsService goodsService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(200).body(goodsService.getAll(user.getId()));
    }

    @PostMapping("/stock_in")
    public ResponseEntity stockIn(@AuthenticationPrincipal MyUser user, @RequestBody @Valid GoodsInsert dto){
        goodsService.stockIn(user, dto);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/stock_out")
    public ResponseEntity stockOut(@AuthenticationPrincipal MyUser user,@RequestBody @Valid GoodsInsert dto){
        goodsService.stockOut(user.getId(), dto);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/totalPrice")
    public ResponseEntity totalPrice(@AuthenticationPrincipal MyUser user,@RequestBody @Valid GoodsInsert dto){
        goodsService.totalPrice(user.getId(), dto);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser user, @PathVariable int id){
        goodsService.delete(user.getId(), id);
        return ResponseEntity.status(200).body("Success");
    }

}
