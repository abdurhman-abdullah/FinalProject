package com.example.packngo.Configuration;

import com.example.packngo.Security.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootConfiguration
@EnableWebSecurity
@RequiredArgsConstructor
public class Configuration {

    private final UserSecurityService userSecurityService;
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(this.userSecurityService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/v1/user/register").permitAll()
                .requestMatchers("/api/v1/contractor/**").hasAuthority("CONTRACTOR")
                .requestMatchers("/api/v1/vehicle/**").hasAuthority("CONTRACTOR")
                .requestMatchers("/api/v1/direct/**").hasAuthority("CONTRACTOR")
                .requestMatchers("/api/v1/warehouse/**").hasAuthority("CONTRACTOR")
                .requestMatchers("/api/v1/customer/**").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/order/**").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/review/**").hasAuthority("CUSTOMER")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();

    }
}
