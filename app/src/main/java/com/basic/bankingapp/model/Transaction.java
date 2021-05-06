package com.basic.bankingapp.model;

public class Transaction {

    private String fromName,toName,amount,status,transactionDate;

    public Transaction() {
    }

    public Transaction(String fromName, String toName, String amount, String status, String transactionDate) {
        this.fromName = fromName;
        this.toName = toName;
        this.amount = amount;
        this.status = status;
        this.transactionDate = transactionDate;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}

