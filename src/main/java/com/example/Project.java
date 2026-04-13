package com.example;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project {
    private String projectID;
    private Employee projectLeader;
    private Calendar startDate;
    private Calendar endDate;
    private List<Employee> employees;
    private List<Activity> activities;

    public Project(String name) {
        this.projectID = name;
        this.employees = new ArrayList<Employee>();
        this.activities = new ArrayList<Activity>();
        this.projectLeader = null;
    }
    public void assignEmploye(Employee employee) {
        this.employees.add(employee);
    }
    public boolean assignProjectLeader(Employee employee) {
        if (this.projectLeader == null) {
            this.projectLeader = employee;
            return true;
        }
        return false;
    }

    public boolean removeEmployee(String ID) {
        for (int i = 0; i < this.employees.size(); i++) {
            if(employees.get(i).getID().equals(ID)) {
                employees.remove(i);
                return true;
            }
        }
        return false;
    }
    public Activity addActivity(Activity activity) {
        activities.add(activity);
        return activity;
    }
}
