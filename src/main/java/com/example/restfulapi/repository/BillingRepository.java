package com.example.restfulapi.repository;

import com.example.restfulapi.model.BillingInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillingRepository extends JpaRepository<BillingInformation, Integer> {

    BillingInformation findByBillNumber(String bill_number);

    List<BillingInformation> findAllByCustomerNumber(String customer_number);


}
