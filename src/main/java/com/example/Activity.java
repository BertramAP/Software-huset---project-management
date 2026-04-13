package com.example;

import java.util.Calendar;
import java.util.HashMap;

public class Activity {
    private String ID;
    private Calendar startDate;
    private Calendar endDate;
    private int budgetHalfHours;
    private HashMap<String, Contribution> contributions;
    private String creatorID;

    public Activity(String ID) {
        this.ID = ID;
        this.contributions = new HashMap<>();
    }

    public String getID() {
        return ID;
    }

    public int getWorkHalfHours() {
        return budgetHalfHours;
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
