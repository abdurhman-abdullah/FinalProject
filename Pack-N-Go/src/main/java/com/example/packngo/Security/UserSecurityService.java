package com.example.packngo.Security;

import com.example.packngo.Models.MyUser;
import com.example.packngo.Repositories.MyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {

    private final MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            MyUser user = myUserRepository.findByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("Wrong username or password");
        }

        return user;
    }

}
