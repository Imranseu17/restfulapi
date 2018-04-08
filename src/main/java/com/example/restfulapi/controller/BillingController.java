package com.example.restfulapi.controller;


import com.example.restfulapi.model.BillingInformation;
import com.example.restfulapi.model.BillingStatus;
import com.example.restfulapi.model.JsonType;
import com.example.restfulapi.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class BillingController {


    @Autowired
    BillingRepository billingRepository;



    @PreAuthorize("hasRole('admin')")
    @GetMapping(value = "/secured/billInformation")
    public List<BillingInformation> findAllBillInformation() {
        return billingRepository.findAll();
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping(value = "/secured/billInformation/{billNumber}")
    public BillingInformation findoneBillInformation(@PathVariable("billNumber") String billNumber) {
        return billingRepository.findByBillNumber(billNumber);
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/secured/updatePaymentBillInformation/")
    public JsonType PayBill(@RequestParam("billNumber") String billNumber,
                            @RequestParam("amount") Float amount) {

        BillingInformation billingInformation = billingRepository.findByBillNumber(billNumber);
        Float paidAmount = billingInformation.getPaid_amount();


        try {

            Float newAmount = paidAmount + amount;
            Float bill_amount = billingInformation.getBill_amount();
            Float vat_amount = billingInformation.getVat_amount();
            Float totalAmount = bill_amount+vat_amount;
            Float due_amount = totalAmount - newAmount;



            if (newAmount >= totalAmount)
                billingInformation.setBillingStatus(BillingStatus.paid);
            else if (newAmount == 0)
                billingInformation.setBillingStatus(BillingStatus.pending);
            else
                billingInformation.setBillingStatus(BillingStatus.due);

            billingInformation.setPaid_amount(newAmount);
            billingInformation.setTotal_amount(totalAmount);
            billingInformation.setDue_amount(due_amount);
            billingInformation.setPay_date(Date.valueOf(LocalDate.now()));
            billingRepository.save(billingInformation);

        } catch (Exception e) {
            e.printStackTrace();
            JsonType jsonType = new JsonType("Unsuccessful",
                    billingInformation.getBillingStatus().getMeaning());
            return jsonType;
        }

        JsonType jsonType = new JsonType("Successful",
                billingInformation.getBillingStatus().getMeaning());

        return jsonType;
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping(value = "/secured/unpaidALLBillInformation/{customerNumber}")
    public List<BillingInformation> findAllUnpaidBillInformation(@PathVariable("customerNumber")
                                                                      String customerNumber) {

        List<BillingInformation> billingInformationpendingList = new ArrayList<>();

        List<BillingInformation> billingInformationList = billingRepository.
                findAllByCustomerNumber(customerNumber);


        for (BillingInformation billInfo : billingInformationList) {

            if (billInfo.getBillingStatus().getMeaning().equals("pending")) {
                billingInformationpendingList.add(billInfo);
            }

            if (billInfo.getBillingStatus().getMeaning().equals("due")) {

                billingInformationpendingList.add(billInfo);
            }
        }

        return billingInformationpendingList;
    }
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/cancelBillInformation/")
    public JsonType updateBillInformation(@RequestParam("billNumber") String billNumber,
                                          @RequestParam(value = "cancelRemarks",
                                                  required = false)
                                                  String cancelRemarks) {

        BillingInformation billingInformation = billingRepository.findByBillNumber(billNumber);
        Float due_amount = billingInformation.getTotal_amount();

        try {
            billingInformation.setRemarks(cancelRemarks);
            billingInformation.setPaid_amount(0);
            billingInformation.setBillingStatus(BillingStatus.cancelled);
            billingInformation.setCancel_date(Date.valueOf(LocalDate.now()));
            billingInformation.setDue_amount(due_amount);

            if (billingInformation.getPay_date().equals(Date.valueOf(LocalDate.now())))
                billingRepository.save(billingInformation);

            else {
                JsonType jsonMessage = new JsonType("Date Expired",
                        "Unable cancel payment");
                return jsonMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();

            JsonType jsonType = new JsonType("Unsuccessful",
                    billingInformation.getBillingStatus().getMeaning());
            return jsonType;
        }

        JsonType jsonType = new JsonType("Successful",
                billingInformation.getBillingStatus().getMeaning());
        return jsonType;
    }


}



