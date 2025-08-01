package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.luv2code.aopdemo.Account;

@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;
    
    private String serviceCode;

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        // Logic to add an account
        System.out.println(getClass() +" : Doing My DB Work: Adding an account");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() +" : DoWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass() +" : in getName()");
        return name;
    }

    public void setName(String name) {        
        System.out.println(getClass() +" : in setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() +" : in getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() +" : in setServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        if (tripWire) {
            throw new RuntimeException("No soup for you!!!");
        }

        List<Account> myAccounts = new ArrayList<>();

        Account temp1 = new Account("John", "Silver");
        Account temp2 = new Account("Madhu", "Platinum");
        Account temp3 = new Account("Luca", "Gold");

        myAccounts.add(temp1);
        myAccounts.add(temp2);
        myAccounts.add(temp3);

        return myAccounts;        
    }

}
