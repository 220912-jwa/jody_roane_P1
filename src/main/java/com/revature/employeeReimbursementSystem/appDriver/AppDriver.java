package com.revature.employeeReimbursementSystem.appDriver;

import com.revature.employeeReimbursementSystem.controllers.*;
import com.revature.employeeReimbursementSystem.dataAccessObjects.*;
import com.revature.employeeReimbursementSystem.services.*;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import static io.javalin.apibuilder.ApiBuilder.*;

public class AppDriver
{
    public static void main(String[] args)
    {
        //DAOs
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ClaimDAO claimDAO = new ClaimDAO();
        AccountDAO accountDAO = new AccountDAO();

        //Services
        AuthenticationService authenticationService = new AuthenticationService(employeeDAO);
        ClaimService claimService = new ClaimService(claimDAO);
        AccountService accountService = new AccountService(accountDAO);

        //Controllers
        AuthenticationController authenticationController = new AuthenticationController(authenticationService);
        ClaimController claimController = new ClaimController(claimService);
        AccountController accountController = new AccountController(accountService);

        // CONFIG
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.addStaticFiles("/ersWebsite", Location.CLASSPATH);}).start(8080);

// ENDPOINTS

    // AUTHENTICATE
        // POST
        app.post("/authenticate/login",authenticationController.loginRequest);
        // DELETE
        app.delete("/deauthenticate/logout",authenticationController.logoutRequest);
    // CLAIM
        // PST
        app.post("/claim/create",claimController.createNewClaimRequest);
        // GET
        app.get("/claim/get/all/approval", claimController.getClaimsByAwardStatusRequest);
        app.get("/claim/get/{id}", claimController.getClaimByIdRequest);
        app.get("/claim/get/all/employee/{employeeId}", claimController.getClaimsByEmployeeIdRequest);
        // patch
        app.patch("claim/update", claimController.updateClaimById);
    // ACCOUNT
        // GET
        app.get("/account/get",accountController.getAccountRequest);
    }
}