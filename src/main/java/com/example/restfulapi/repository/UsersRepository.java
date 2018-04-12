package com.example.restfulapi.repository;

import com.example.restfulapi.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Integer> {


    Users findByUserName(String name);
}
