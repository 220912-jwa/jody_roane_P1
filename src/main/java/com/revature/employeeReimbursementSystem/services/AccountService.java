package com.revature.employeeReimbursementSystem.services;

import com.revature.employeeReimbursementSystem.dataAccessObjects.AccountDAO;
import com.revature.employeeReimbursementSystem.entities.Account;

public class AccountService {
    private AccountDAO accountDAO;
    public AccountService(AccountDAO accountDAO){this.accountDAO = accountDAO;}
    public Account getAccountByEmployeeId(int employeeId)
    {
        System.out.println("Starting Account Service: get account by employee id.");
        Account account = accountDAO.getAccountByEmployeeId(employeeId);
        System.out.println("The employeeId input retrieved this record:" + account);
        System.out.println("Account Service Successful: returning account by employee id.");
        return account;
    }
}
