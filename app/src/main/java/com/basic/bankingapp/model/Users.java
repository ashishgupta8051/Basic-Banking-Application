package com.basic.bankingapp.model;

public class Users {

    private String name;
    private String phone;
    private int balance;
    private String email;
    private int accountNumber;
    private String ifsc_Code;
    private String address;
    private String dob;

    public Users(){}

    public Users(String name, String phone, int balance, String email, int accountNumber, String ifsc_Code, String address, String dob) {
        this.name = name;
        this.phone = phone;
        this.balance = balance;
        this.email = email;
        this.accountNumber = accountNumber;
        this.ifsc_Code = ifsc_Code;
        this.address = address;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfsc_Code() {
        return ifsc_Code;
    }

    public void setIfsc_Code(String ifsc_Code) {
        this.ifsc_Code = ifsc_Code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}

