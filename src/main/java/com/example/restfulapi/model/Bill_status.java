package com.example.restfulapi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bill_status_info")
public class Bill_status {
    @Id
    @GeneratedValue
    private int id;
    private int status;
    private String meaning;

    @OneToMany
    @JoinColumn(name = "bill_status")
    private List<BillInformation> billInformationList = new ArrayList<>();

    public Bill_status(int status, String meaning) {
        this.status = status;
        this.meaning = meaning;
    }

    public Bill_status() {
    }

    public int getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getMeaning() {
        return meaning;
    }


    @Override
    public String toString() {
        return "Bill_status{" +
                "id=" + id +
                ", status=" + status +
                ", meaning='" + meaning + '\'' +
                ", billInformationList=" + billInformationList +
                '}';
    }
}
