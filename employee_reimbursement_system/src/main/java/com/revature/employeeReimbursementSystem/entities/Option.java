package com.revature.employeeReimbursementSystem.entities;

public class Option {
    private int id;
    private String option;
    public Option(int id, String option){this.id = id; this.option = option;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
    @Override
    public String toString()
    {
        return "Option(\n" +
                "\nid: " + getId() +
                "\noption: '" + getOption() + '\'' +
                "\n{";
    }
}
