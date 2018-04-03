package com.example.restfulapi.model;

import javax.persistence.Table;

@Table(name = "bill_status_info")
public enum BillStatus {

    none(0,"none"),
    pending(1,"pending"),
    paid(2,"paid"),
    due(3,"due"),
    cancelled(4,"cancelled");




    String meaning;
    int key;

    BillStatus(int key,String meaning) {
        this.meaning = meaning;
        this.key = key;
    }

    BillStatus() {
    }

    public String getMeaning() {
        return meaning;
    }

    public int getKey() {
        return key;
    }





    @Override
    public String toString() {
        return "BillStatus{" +
                "meaning='" + meaning + '\'' +
                ", key=" + key +
                '}';
    }
}
