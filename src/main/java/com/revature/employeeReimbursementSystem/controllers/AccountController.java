package com.revature.employeeReimbursementSystem.controllers;

import com.revature.employeeReimbursementSystem.entities.Account;
import com.revature.employeeReimbursementSystem.entities.Claim;
import com.revature.employeeReimbursementSystem.services.AccountService;
import com.revature.employeeReimbursementSystem.services.ClaimService;
import io.javalin.http.Handler;

public class AccountController {
    private AccountService accountService;
    public AccountController(AccountService accountService){this.accountService = accountService;}
    public Handler getAccountRequest = ctx ->
    {
        System.out.println("Handling: get account request.");
        int employeeId = Integer.parseInt(ctx.pathParam("id"));
        Account account = accountService.getAccountByEmployeeId(employeeId);
        if(account != null)
        {
            System.out.println("Request Handled: sending status: 200 (ok).");
            ctx.status(200);
            ctx.json(account);
        }
        else
        {
            System.out.println("Request Handled: sending status: 403 (forbidden).");
            ctx.status(403);
        }
    };
}
