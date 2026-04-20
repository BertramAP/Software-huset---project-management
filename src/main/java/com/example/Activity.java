package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activity {
    private String ID;
    private LocalDate startDate;
    private LocalDate endDate;
    private int budgetHalfHours;
    private HashMap<String, List<Contribution> > contributions;
    private String creatorID;
    private ArrayList<Employee> employees;

    public Activity(String ID, LocalDate startDate, LocalDate endDate, int budgetHalfHours, String creatorID) {
        this.ID = ID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetHalfHours = budgetHalfHours;
        this.contributions = new HashMap<String, List<Contribution>>();
        this.creatorID = creatorID;
        this.employees = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public void addContribution(String employeeID, Contribution contribution) {
        List<Contribution> employeeContributions = contributions.get(employeeID);

        if(employeeContributions == null) {
            employeeContributions = new ArrayList<Contribution>();
            contributions.put(employeeID, employeeContributions);
        }
        contributions.get(employeeID).add(contribution);

    }

    public int getWorkHalfHours() {
        return getTimeUsed();
    }

    public List<Contribution> getContributions(String id) {
        return contributions.get(id);
    }

    public int getTimeUsed() {
        int totalTime = 0;
        for (List<Contribution> cList : contributions.values()) {
            for (Contribution c : cList) {
                totalTime += c.getWorkTime();
            }
        }
        return totalTime;
    }

    public int getTimeUsed(String id) {
        int totalTime = 0;
        List<Contribution> cList = contributions.get(id);
        for(Contribution c : cList) {
            totalTime += c.getWorkTime();
        }
        return totalTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void addEmployee(Employee employee) {
        if (hasEmployee(employee.getID())) {
            throw new IllegalArgumentException("Employee is already assigned to the activity");
        }
        employees.add(employee);
    }

    public boolean hasEmployee(String employeeID) {
        for (Employee e : employees) {
            if (e.getID().equals(employeeID)) {
                return true;
            }
        }
        return false;
    }
}
