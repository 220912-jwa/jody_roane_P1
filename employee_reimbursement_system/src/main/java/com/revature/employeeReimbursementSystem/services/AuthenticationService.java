package com.revature.employeeReimbursementSystem.services;

import com.revature.employeeReimbursementSystem.dataAccessObjects.EmployeeDAO;
import com.revature.employeeReimbursementSystem.entities.Employee;

public class AuthenticationService {
    private EmployeeDAO employeeDAO;
    public AuthenticationService (EmployeeDAO employeeDAO){this.employeeDAO = employeeDAO;}
    // Authenticate login service
    public Employee authenticateLogin(String usernameInput, String passwordInput)
    {
        System.out.println("Starting authentication service: Authenticate Login.");
        Employee authenticatedEmployee = employeeDAO.getEmployeeByUsername(usernameInput);
        System.out.println("Front end username input retrieved this record:" + authenticatedEmployee);
        if(authenticatedEmployee.getPassword().equals(passwordInput))
        {
            System.out.println("Authentication successful: returning authenticatedEmployee object, ending authentication service.");
            return authenticatedEmployee;
        }
        else
        {
         System.out.println("Authentication failed: returning null, ending authentication service.");
        }
        return null;
    }
}
