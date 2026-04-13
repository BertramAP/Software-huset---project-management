package com.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Activity {
    private String ID;
    private Calendar startDate;
    private Calendar endDate;
    private int budgetHalfHours;
    private HashMap<String, Contribution> contributions;
    private String creatorID;
    private ArrayList<Employee> employees;

    public Activity(String ID, Calendar startDate, Calendar endDate, int budgetHalfHours, String creatorID) {
        this.ID = ID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetHalfHours = budgetHalfHours;
        this.contributions = new HashMap<String, Contribution>();
        this.creatorID = creatorID;
        this.employees = new ArrayList<>(); 
    }


    public String getID() {
        return ID;
    }
    public void addContribution(String employeeID, Contribution contribution) {
    contributions.put(employeeID, contribution);
    }   


    public int getWorkHalfHours() {
        return getTimeUsed();
    }


    public Contribution getContribution(String id) {
        return contributions.get(id);
    }

    public int getTimeUsed() {
        return contributions.values().stream().mapToInt(c -> c.getWorkTime()).sum();
    }

    public int getTimeUsed(String id) {
        Contribution c = contributions.get(id);
        return c != null ? c.getWorkTime() : 0;
    }
}
