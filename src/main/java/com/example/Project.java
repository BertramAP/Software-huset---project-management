package com.example;
import com.example.errors.DuplicateActivitiesIDException;

import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class Project {
    private final int id;
    private final String name;
    private Employee projectLeader;
    private LocalDate startDate;
    private LocalDate endDate;
    public List<Employee> employees;
    private List<Activity> activities;

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
        this.employees = new ArrayList<Employee>();
        this.activities = new ArrayList<Activity>();
        this.projectLeader = null;
    }

    public void assignEmployee(Employee employee) {
        if(!hasEmployee(employee.getID())) {
            this.employees.add(employee);
        }
    }
    public void assignProjectLeader(Employee employee) { // Written by AK
        if (this.projectLeader != null) {
            throw new IllegalStateException("Project already has a project leader");
        }
        // Add the project leader to employeelist
        if (!this.employees.contains(employee)) {
            assignEmployee(employee);
        }
        this.projectLeader = employee;
    }

    public void removeEmployee(String ID) { // Written by BAP & AK
        assert this.employees != null; // Pre-condition
        assert ID != null; // Pre-condition
        int originalSize = this.employees.size(); // For post-condition

        // Pre-condition
        for (Employee employee: this.employees) {
            assert employee != null;
            assert employee.getID() != null;
        }

        for (int i = 0; i < this.employees.size(); i++) {
            if (employees.get(i).getID().equals(ID)) {
                employees.remove(i);
                assert this.employees.size() == (originalSize - 1); // post-condition
                return;
            }
        }
        throw new IllegalArgumentException("Employee is not assigned to the project");
    }

    public boolean hasEmployee(String ID) { // Written by AK
        return employees.stream().anyMatch(e -> e.getID().equals(ID));
    }

    public void addActivity(Activity activity) { // Written by BAP
        if(getActivity(activity.getID()) == null) {
            activities.add(activity);
        } else {
            throw new DuplicateActivitiesIDException("Activity already exists");
        }

    }
    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }

    public Employee getProjectLeader() { // Written by AK
        return projectLeader;
    }
    public Activity getActivity(String ID) { // Written by AK
        for (Activity a : activities) {
            if (a.getID().equals(ID)) {
                return a;
            }
        }
        return null;
    }

    public Report generateReport() {
        return new Report(this); // Give current project to the report
    }

    public int getTimeUsed() { // Written by BAP
        int time = 0;
        for(Activity a: activities) {
            time += a.getTimeUsed();
        }
        return time;
    }

    public int getTimeUsed(String activityID) { // Written by BAP
        return getActivity(activityID).getTimeUsed();
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public boolean assignToActivity(String activityID, Employee emp) { // Written by BAP
        Activity activity = getActivity(activityID);
        if (activity == null) return false;
        if (!emp.isAvailable(activity.getStartDate().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR), activity.getStartDate().getYear())) throw new IllegalArgumentException("Employee is not available");
        if (!hasEmployee(emp.getID())) assignEmployee(emp);
        activity.addEmployee(emp);
        emp.assignToActivity(activity); // Add activity to employees own personal list (Written by MJ)
        return true;
    }
}