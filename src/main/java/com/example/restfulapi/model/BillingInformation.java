package com.example.restfulapi.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "bill_info")
public class BillingInformation {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "customer_number")
    private String customerNumber;
    @Column(name = "bill_number")
    private String billNumber;
    private String utility_trnxn_id;
    private String bank_trnxn_id;
    private int billType;
    @Column(name = "issueDate")
    private Date issueDate;
    private float bill_amount;
    private float vat_amount;
    private float total_amount;
    private float paid_amount;
    private Date pay_date;
    private String paid_by;
    private float due_amount;
    private Date due_date;

    @Column(name = "bill_status")
    @Enumerated(EnumType.ORDINAL)
    private BillingStatus billingStatus;

    private String cancelled_by;
    private Date cancel_date;
    private String remarks;

    public BillingInformation(String customerNumber, String billNumber, String utility_trnxn_id, String bank_trnxn_id, int billType, Date issueDate, float bill_amount, float vat_amount, float total_amount, float paid_amount, Date pay_date, String paid_by, float due_amount, Date due_date,
                              BillingStatus billingStatus, String cancelled_by, Date cancel_date, String remarks) {
        this.customerNumber = customerNumber;
        this.billNumber = billNumber;
        this.utility_trnxn_id = utility_trnxn_id;
        this.bank_trnxn_id = bank_trnxn_id;
        this.billType = billType;
        this.issueDate = issueDate;
        this.bill_amount = bill_amount;
        this.vat_amount = vat_amount;
        this.total_amount = total_amount;
        this.paid_amount = paid_amount;
        this.pay_date = pay_date;
        this.paid_by = paid_by;
        this.due_amount = due_amount;
        this.due_date = due_date;
        this.billingStatus = billingStatus;
        this.cancelled_by = cancelled_by;
        this.cancel_date = cancel_date;
        this.remarks = remarks;
    }

    public BillingInformation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getUtility_trnxn_id() {
        return utility_trnxn_id;
    }

    public void setUtility_trnxn_id(String utility_trnxn_id) {
        this.utility_trnxn_id = utility_trnxn_id;
    }

    public String getBank_trnxn_id() {
        return bank_trnxn_id;
    }

    public void setBank_trnxn_id(String bank_trnxn_id) {
        this.bank_trnxn_id = bank_trnxn_id;
    }

    public int getBillType() {
        return billType;
    }

    public void setBillType(int billType) {
        this.billType = billType;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public float getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(float bill_amount) {
        this.bill_amount = bill_amount;
    }

    public float getVat_amount() {
        return vat_amount;
    }

    public void setVat_amount(float vat_amount) {
        this.vat_amount = vat_amount;
    }

    public float getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(float total_amount) {
        this.total_amount = total_amount;
    }

    public float getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(float paid_amount) {
        this.paid_amount = paid_amount;
    }

    public Date getPay_date() {
        return pay_date;
    }

    public void setPay_date(Date pay_date) {
        this.pay_date = pay_date;
    }

    public String getPaid_by() {
        return paid_by;
    }

    public void setPaid_by(String paid_by) {
        this.paid_by = paid_by;
    }

    public float getDue_amount() {
        return due_amount;
    }

    public void setDue_amount(float due_amount) {
        this.due_amount = due_amount;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public BillingStatus getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(BillingStatus billingStatus) {
        this.billingStatus = billingStatus;
    }

    public String getCancelled_by() {
        return cancelled_by;
    }

    public void setCancelled_by(String cancelled_by) {
        this.cancelled_by = cancelled_by;
    }

    public Date getCancel_date() {
        return cancel_date;
    }

    public void setCancel_date(Date cancel_date) {
        this.cancel_date = cancel_date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "BillingInformation{" +
                "id=" + id +
                ", customerNumber='" + customerNumber + '\'' +
                ", billNumber='" + billNumber + '\'' +
                ", utility_trnxn_id='" + utility_trnxn_id + '\'' +
                ", bank_trnxn_id='" + bank_trnxn_id + '\'' +
                ", billType=" + billType +
                ", issueDate=" + issueDate +
                ", bill_amount=" + bill_amount +
                ", vat_amount=" + vat_amount +
                ", total_amount=" + total_amount +
                ", paid_amount=" + paid_amount +
                ", pay_date=" + pay_date +
                ", paid_by='" + paid_by + '\'' +
                ", due_amount=" + due_amount +
                ", due_date=" + due_date +
                ", billingStatus=" + billingStatus +
                ", cancelled_by='" + cancelled_by + '\'' +
                ", cancel_date=" + cancel_date +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
