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

    public Activity(String ID, LocalDate startDate, LocalDate endDate, int budgetHalfHours, String creatorID) { // Written by AK
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

    public void addContribution(Contribution contribution) {
        String employeeId = contribution.getEmployee().getID();
        List<Contribution> employeeContributions = contributions.get(employeeId);

        if (employeeContributions == null) {
            employeeContributions = new ArrayList<Contribution>();
            contributions.put(employeeId, employeeContributions);
        }
        contributions.get(employeeId).add(contribution);

    }

    public int getWorkHalfHours() { // Written by AK
        return getTimeUsed();
    }

    public List<Contribution> getContributions(String id) { // Written by BAP
        return contributions.get(id);
    }

    public int getTimeUsed() { // Written by BAP
        int totalTime = 0;
        for (List<Contribution> cList : contributions.values()) {
            for (Contribution c : cList) {
                totalTime += c.getWorkTime();
            }
        }
        return totalTime;
    }

    public int getTimeUsed(String id) { // Written by BAP
        int totalTime = 0;
        List<Contribution> cList = contributions.get(id);
        for(Contribution c : cList) {
            totalTime += c.getWorkTime();
        }
        return totalTime;
    }

    public LocalDate getStartDate() { // Written by BAP
        return startDate;
    }

    public LocalDate getEndDate() {return this.endDate; }

    public void addEmployee(Employee employee) { // Written by AK
        if (hasEmployee(employee.getID())) {
            throw new IllegalArgumentException("Employee is already assigned to the activity");
        }
        employees.add(employee);
    }

    public boolean hasEmployee(String employeeID) { // Written by AK
        for (Employee e : employees) {
            if (e.getID().equals(employeeID)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Employee> getEmployees() { // Written by BAP
        return employees;
    }
}
