package com.example.restfulapi.repository;

import com.example.restfulapi.model.Bill_status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Status_repository extends JpaRepository<Bill_status, Integer> {

}
