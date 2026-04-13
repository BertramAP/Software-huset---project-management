package com.example;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class Activity {
    private String ID;
    private Calendar startDate;
    private Calendar endDate;
    private int budgetHalfHours;
    private HashMap<String, Contribution> contributions;
    private String creatorID;
    private List<String> employees;

    public Activity(String ID, Calendar startDate, Calendar endDate, int budgetHalfHours, String creatorID) {
        this.ID = ID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetHalfHours = budgetHalfHours;
        this.contributions = new HashMap<String, Contribution>();
        this.creatorID = creatorID;
        this.employees = new ArrayList<String>();
    }

    public String getID() {
        return ID;
    }
    public void addContribution(String employeeID, Contribution contribution) {
        contributions.put(employeeID, contribution);
    }

    public int getWorkHalfHours() {
        int totalHalfHours = 0;
        for (Contribution contribution : contributions.values()) {
            totalHalfHours += contribution.getWorkTime();
        }
        return totalHalfHours;
    }

    public Contribution getContribution(String ID) {
        return contributions.get(ID);
    }

    public int getTimeUsed() {
        int totalHalfHours = 0;
        for (Contribution contribution : contributions.values()) {
            totalHalfHours += contribution.getWorkTime();
        }
        return totalHalfHours;
    }
    public int getTimeUsed(String ID) {
        if (contributions.containsKey(ID)) {
            return contributions.get(ID).getWorkTime();
        }
        return 0;
    }
}
