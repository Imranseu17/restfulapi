package com.example.restfulapi.service;

import com.example.restfulapi.model.CustomUserDetails;
import com.example.restfulapi.model.Users;
import com.example.restfulapi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUsersDetailsService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUser = usersRepository.findByUserName(username);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("User Name not Found"));

        return optionalUser.map(CustomUserDetails::new).get();
    }
}
