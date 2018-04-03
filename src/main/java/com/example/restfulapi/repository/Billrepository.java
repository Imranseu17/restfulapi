package com.example.restfulapi.repository;

import com.example.restfulapi.model.BillInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Billrepository extends JpaRepository<BillInformation,Integer> {

   BillInformation findByBillNumber(String bill_number);
   List<BillInformation> findAllByCustomerNumber(String customer_number);


}
