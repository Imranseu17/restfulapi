package com.example.restfulapi.controller;


import com.example.restfulapi.model.BillInformation;
import com.example.restfulapi.model.Bill_status;
import com.example.restfulapi.model.JsonType;
import com.example.restfulapi.repository.Billrepository;
import com.example.restfulapi.repository.Status_repository;
import com.example.restfulapi.service.BillInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class BillingController
{

    @Autowired
    BillInformationService billInformationService;
    @Autowired
    Billrepository billrepository;
    @Autowired
    Status_repository status_repository;



    @GetMapping(value = "/bill_Information")
    public List<BillInformation> findAllBillInformation() {
        return billrepository.findAll();
    }

    @GetMapping(value = "/bill_Information/{bill_number}")
    public BillInformation findoneBillInformation(@PathVariable("bill_number") String bill_number) {
        return billrepository.findByBillNumber(bill_number);
    }

    @GetMapping("/bill_Information/{bill_number}/{amount}")
    public JsonType PayBill(@PathVariable("bill_number") String bill_number,
                            @PathVariable("amount") Float Amount) {
        BillInformation billInformation = billrepository.findByBillNumber(bill_number);
        Float paidAmount = billInformation.getPaid_amount();
        Float totalAmount = billInformation.getTotal_amount();

        try {
            billInformation.setPaid_amount(Amount);
            Float newAmount = paidAmount + Amount;


            if (newAmount >= totalAmount)
                billInformation.setBillStatus(status_repository.getOne(2));
            else if (newAmount == 0)
                billInformation.setBillStatus(status_repository.getOne(1));
            else
                billInformation.setBillStatus(status_repository.getOne(3));

            Float billAmount = billInformation.getBill_amount();
            Float currentAmount = billInformation.getPaid_amount();
            Float dueAmount = billAmount - currentAmount;
            billInformation.setDue_amount(dueAmount);
            billInformation.setPay_date(Date.valueOf(LocalDate.now()));
            billrepository.save(billInformation);

        } catch (Exception e) {
            e.printStackTrace();
            JsonType jsonType = new JsonType("Unsuccessful",
                    billInformation.getBillStatus().getMeaning());
            return jsonType;
        }

        JsonType jsonType = new JsonType("Successful",
                billInformation.getBillStatus().getMeaning());

        return jsonType;
    }


    @GetMapping(value = "/bill_Informations/{customer_number}")
    public List<BillInformation> findAllUnpaidBillInformation(@PathVariable("customer_number")
                                                                      String customer_number) {

        List<BillInformation> billInformationpendingList = new ArrayList<>();

        List<BillInformation> billInformationList = billrepository.
                findAllByCustomerNumber(customer_number);


        for (BillInformation billInfo : billInformationList) {

            if (billInfo.getBillStatus().getMeaning().equals("pending")) {
                billInformationpendingList.add(billInfo);
            }

            if (billInfo.getBillStatus().getMeaning().equals("due")) {

                billInformationpendingList.add(billInfo);
            }
        }

        return billInformationpendingList;
    }

    @GetMapping("/updatebill_Information/{bill_number}/{cancel_remarks}")
    public JsonType updateBillInformation(@PathVariable("bill_number") String bill_number,
                                          @PathVariable("cancel_remarks")
                                                  String cancel_remarks) {

        BillInformation billInformation = billrepository.findByBillNumber(bill_number);
        Float due_amount = billInformation.getBill_amount();

        try {
            billInformation.setRemarks(cancel_remarks);
            billInformation.setPaid_amount(0);
            billInformation.setBillStatus(status_repository.getOne(1));
            billInformation.setCancel_date(Date.valueOf(LocalDate.now()));
            billInformation.setDue_amount(due_amount);

            if (billInformation.getPay_date().equals(Date.valueOf(LocalDate.now())))
                billrepository.save(billInformation);

            else {
                JsonType jsonMessage = new JsonType("Date Expired",
                        "Unable cancel payment");
                return jsonMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();

            JsonType jsonType = new JsonType("Unsuccessful",
                    billInformation.getBillStatus().getMeaning());
            return jsonType;
        }

        JsonType jsonType = new JsonType("Successful",
                billInformation.getBillStatus().getMeaning());
        return jsonType;
    }

    @GetMapping("/updatebill_Information/{bill_number}")
    public JsonType cancelBillInformation(@PathVariable("bill_number") String bill_number) {

        BillInformation billInformation = billrepository.findByBillNumber(bill_number);
        Float due_amount = billInformation.getBill_amount();

        try {
            billInformation.setPaid_amount(0);
            billInformation.setBillStatus(status_repository.getOne(1));
            billInformation.setCancel_date(Date.valueOf(LocalDate.now()));
            billInformation.setDue_amount(due_amount);

            if (billInformation.getPay_date().equals(Date.valueOf(LocalDate.now())))
                billrepository.save(billInformation);
            else {
                JsonType jsonMessage = new JsonType("Date Expired",
                        "Unable cancel payment");
                return jsonMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();

            JsonType jsonType = new JsonType("Unsuccessful",
                    billInformation.getBillStatus().getMeaning());
            return jsonType;
        }

        JsonType jsonType = new JsonType("Successful",
                billInformation.getBillStatus().getMeaning());
        return jsonType;
    }
}



