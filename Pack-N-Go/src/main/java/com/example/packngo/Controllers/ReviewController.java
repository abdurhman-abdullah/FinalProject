package com.example.packngo.Controllers;

import com.example.packngo.Models.Review;
import com.example.packngo.Services.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(reviewService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid Review review){
        reviewService.add(review);
        return ResponseEntity.status(200).body("Success");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody @Valid Review review){
        reviewService.update(id,review);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        reviewService.delete(id);
        return ResponseEntity.status(200).body("Success");
    }
}
