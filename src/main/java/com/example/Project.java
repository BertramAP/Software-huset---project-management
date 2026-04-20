package com.example;
import com.example.errors.DuplicateActivitiesIDException;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class Project {
    private String projectID;
    private Employee projectLeader;
    private LocalDate startDate;
    private LocalDate endDate;
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
    public void assignProjectLeader(Employee employee) {
        if (this.projectLeader != null) {
            throw new IllegalStateException("Project already has a project leader");
        }
        this.projectLeader = employee;
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
    public void addActivity(Activity activity) {
        if(getActivity(activity.getID()) == null) {
            activities.add(activity);
        } else {
            throw new DuplicateActivitiesIDException("Activity already exists");
        }

    }
    public String getID() {
        return projectID;
    }

    public Employee getProjectLeader() {
        return projectLeader;
    }
    public Activity getActivity(String ID) {
        for (Activity a : activities) {
            if (a.getID().equals(ID)) {
                return a;
            }
        }
        return null;
    }


    public String generateReport() {
        int totalHalfHours = getTimeUsed();
        String hours = Double.toString(totalHalfHours / 2.);
        if (hours.endsWith(".0")) hours = hours.substring(0, hours.length()-2);
        return "total time spent: " + hours + " hours";
    }
    public int getTimeUsed() {
        int time = 0;
        for(Activity a: activities) {
            time += a.getTimeUsed();
        }
        return time;
    }

    public int getTimeUsed(String activityID) {
        return getActivity(activityID).getTimeUsed();
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public boolean assignToActivity(String activityID, Employee emp) {
        try {
            getActivity(activityID).addEmployee(emp);
        } catch (Exception e) {
            return false;
        }
    return true;
    }
}
