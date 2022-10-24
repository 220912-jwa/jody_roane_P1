package com.revature.employeeReimbursementSystem.entities;

import java.sql.Date;

public class Claim {
    private int id;
    private String firstName;
    private String lastname;
    private String role;
    private int employeeId;
    private double balance;
    private String event;
    private int coverage;
    private String description;
    private String location;
    private double cost;
    private double reimbursement;
    private Date startDate;
    private Date endDate;
    private String passingGrade;
    private String finalGrade;
    private String claimStatus;
    private boolean awardStatus;
    private int priority;
    public Claim(){}
    public Claim(int id,String firstName, String lastname, String role, int employeeId, double balance, String event, int coverage,
                 String description, String location, double cost, double reimbursement, Date startDate, Date endDate, String passingGrade,
                 String finalGrade, String claimStatus, boolean awardStatus, int priority)
    {
      this.id = id;
      this.firstName = firstName;
      this.lastname = lastname;
      this.role = role;
      this.employeeId = employeeId;
      this.balance = balance;
      this.event = event;
      this.coverage = coverage;
      this.description = description;
      this.location = location;
      this.cost = cost;
      this.reimbursement = reimbursement;
      this.startDate = startDate;
      this.endDate = endDate;
      this.passingGrade = passingGrade;
      this.finalGrade = finalGrade;
      this.claimStatus = claimStatus;
      this.awardStatus = awardStatus;
      this.priority = priority;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employee_id) {
        this.employeeId = employee_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getCoverage() {
        return coverage;
    }

    public void setCoverage(int coverage) {
        this.coverage = coverage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(double reimbursement) {
        this.reimbursement = reimbursement;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPassingGrade() {
        return passingGrade;
    }

    public void setPassingGrade(String passingGrade) {
        this.passingGrade = passingGrade;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public boolean isAwardStatus() {
        return awardStatus;
    }

    public void setAwardStatus(boolean awardStatus) {
        this.awardStatus = awardStatus;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    @Override
    public String toString()
    {
        return "Claim{\n" +
                "\nid: " + getId() +
                "\nfirstName: '" + getFirstName() + '\'' +
                "\nlastName: '" + getLastname() + '\'' +
                "\nrole: '" + getRole() + '\'' +
                "\nemployeeId: " + getEmployeeId() +
                "\nbalance: " + getEmployeeId() +
                "\nevent: '" + getEvent() + '\'' +
                "\ncoverage: " + getCoverage() +
                "\ndescription: '" + getDescription() + '\'' +
                "\nlocation: '" + getLocation() + '\'' +
                "\ncost: " + getCost() +
                "\nreimbursement: " + getReimbursement() +
                "\nstartDate: " + getStartDate() +
                "\nendDate: " + getEndDate() +
                "\npassingGrade: '" + getPassingGrade() + '\'' +
                "\nclaimStatus: '" + getClaimStatus() + '\'' +
                "\nawardStatus: '" + isAwardStatus() + '\'' +
                "\npriority: " +getPriority() +
                "\n}";

    }
}
