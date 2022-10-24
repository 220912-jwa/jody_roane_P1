package com.revature.employeeReimbursementSystem.entities;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String role;
    private String username;
    private String password;

    public Employee(){}
    public Employee(int id, String firstName, String lastName, String role, String username, String password)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString()
    {
        return "Employee\n" +
                "{\n" +
                "id: " + this.getId() +
                "\nfirstName: '" +this.getFirstName() + '\'' +
                "\nlastName: '" +this.getLastName() + '\'' +
                "\nrole: '" +this.getRole() + '\'' +
                "\nusername: '" +this.getUsername() + '\'' +
                "\npassword: '" +this.getPassword() + '\''+
                "\n}";
    }
}
