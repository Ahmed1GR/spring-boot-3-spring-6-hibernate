package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{

    @Override
    public boolean addSillyMember() {
        // Logic to add an account
        System.out.println(getClass() +" : Doing My DB Work: Adding a membership account");
        return true;
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() +" : I'm Going to sleep zzzzzzz");
    }

}
