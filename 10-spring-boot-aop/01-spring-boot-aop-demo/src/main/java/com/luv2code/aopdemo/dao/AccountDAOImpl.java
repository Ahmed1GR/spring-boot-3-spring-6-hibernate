package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

import com.luv2code.aopdemo.Account;

@Repository
public class AccountDAOImpl implements AccountDAO{

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

}
