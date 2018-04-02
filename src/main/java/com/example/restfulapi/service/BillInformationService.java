package com.example.restfulapi.service;


import com.example.restfulapi.model.BillInformation;

import java.util.List;

public interface BillInformationService {

    BillInformation findBybill_number(String bill_number);

    List<BillInformation> findAllInformation();






}