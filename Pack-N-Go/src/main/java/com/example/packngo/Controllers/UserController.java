package com.example.packngo.Controllers;

import com.example.packngo.Models.MyUser;
import com.example.packngo.Services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/getAll")
    public ResponseEntity getAll(@AuthenticationPrincipal MyUser userId){
        return ResponseEntity.status(200).body(this.userService.getAll(userId.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity add(@Valid @RequestBody MyUser user){
        this.userService.add(user);
        return ResponseEntity.status(200).body("user added");
    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal MyUser userId, @Valid @RequestBody MyUser user){
        this.userService.update(userId.getId(), user);
        return ResponseEntity.status(200).body("user updated");
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser userId){
        this.userService.delete(userId.getId());
        return ResponseEntity.status(200).body("user deleted");
    }

}
