package com.example.springsecurity2.config;

import com.example.springsecurity2.model.UserInfo;
import com.example.springsecurity2.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    UserInfoRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserInfo userInfo = repository.findByName(username)
               .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return new UserInfoUserService(userInfo);
    }
}
