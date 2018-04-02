package com.example.restfulapi.service;


import com.example.restfulapi.model.BillInformation;
import com.example.restfulapi.repository.Billrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillInformationServiceImplementation implements BillInformationService {

    @Autowired
    Billrepository billrepository;

    private static List<BillInformation> billInformationList = new ArrayList<>();




    @Override
    public BillInformation findBybill_number(String bill_number) {
        for(BillInformation billInformation : billInformationList){
            if(billInformation.getBillNumber().equalsIgnoreCase(bill_number)){
                return billInformation;
            }
        }
        return null;
    }

    @Override
    public List<BillInformation> findAllInformation() {
        return billrepository.findAll();
    }




}
