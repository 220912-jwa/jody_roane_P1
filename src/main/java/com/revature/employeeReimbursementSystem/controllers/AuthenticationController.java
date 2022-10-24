package com.revature.employeeReimbursementSystem.controllers;

import com.revature.employeeReimbursementSystem.entities.Employee;
import com.revature.employeeReimbursementSystem.services.AuthenticationService;
import io.javalin.http.Handler;

public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {this.authenticationService = authenticationService;}

    public Handler loginRequest = ctx ->
    {
        System.out.println("Handling: login request.");
        Employee frontEndUser = ctx.bodyAsClass(Employee.class);
        Employee authenticatedEmployee = authenticationService.authenticateLogin(frontEndUser.getUsername(), frontEndUser.getPassword());
        if (authenticatedEmployee != null) {
            System.out.println("Request handled: sending status: 200 (ok), configuring session settings, sending this JSON to front End: authenticated" + authenticatedEmployee.toString());
            ctx.status(200);
            ctx.sessionAttribute("userCredentials", authenticatedEmployee);
            ctx.json(authenticatedEmployee);
        }
        else
        {
            System.out.println("Request Handled:, sending status: 401 (unauthorized).");
            ctx.status(401);
        }
    };
    public Handler logoutRequest =  ctx ->
    {
      System.out.println("Handling: logout request.");
      String deauthenticatedEmployee = ctx.sessionAttribute("userCredentials");
      ctx.req.getSession().invalidate();
      if(deauthenticatedEmployee == null)
      {
          System.out.println("Request Handled: sending status: 200 (ok).");
          ctx.status(200);
      }
      else
      {
          System.out.println("Request Handled: sending status: 403 (forbidden).");
          ctx.status(403);
      }
    };
}
