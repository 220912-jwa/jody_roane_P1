package com.revature.employeeReimbursementSystem.entities;

public class Account {
    private int id;
    private int employeeId;
    private double total;
    private double pending;
    private double awarded;
    private double balance;
    public Account(int id, int employeeId, double total, double pending, double awarded, double balance)
    {
        this.id = id;
        this.employeeId = employeeId;
        this.total = total;
        this.pending = pending;
        this.awarded = awarded;
        this.balance = balance;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPending() {
        return pending;
    }

    public void setPending(double pending) {
        this.pending = pending;
    }

    public double getAwarded() {
        return awarded;
    }

    public void setAwarded(double awarded) {
        this.awarded = awarded;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    @Override
    public String toString()
    {
        return "Account{\n" +
                "\nid: "+ getId() +
                "\nemployee_id: " + getEmployeeId() +
                "\ntotal: " + getTotal() +
                "\npending: " + getPending() +
                "\nawarded: " + getAwarded() +
                "\nbalance: " + getBalance() +
                "\n{";
    }
}
