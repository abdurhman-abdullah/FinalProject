package com.example.packngo.Services;

import com.example.packngo.Exceptions.ApiException;
import com.example.packngo.Models.MyUser;
import com.example.packngo.Repositories.MyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final MyUserRepository myUserRepository;

    public MyUser getAll(Integer userId){
        MyUser customer = myUserRepository.findById(userId)
                .orElseThrow(() -> new ApiException("user not found"));

        return customer;
    }

    public void add(MyUser user){
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole(user.getRole());
        myUserRepository.save(user);
    }

    public void update(int userId, MyUser user){
        MyUser findUser = myUserRepository.findById(userId)
                .orElseThrow(() -> new ApiException("user not found"));

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        findUser.setPassword(hash);
        myUserRepository.save(findUser);
    }

    public void delete(int userId){
        MyUser findUser = myUserRepository.findById(userId)
                .orElseThrow(() -> new ApiException("user not found"));

        myUserRepository.delete(findUser);
    }
}
