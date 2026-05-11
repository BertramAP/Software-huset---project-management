package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.errors.DuplicateProjectNameException;
public class ProjectApp {
    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();

    // Written by DIS, contracted (assert statements) by MJ
    public Project createProject(String name) throws DuplicateProjectNameException {
        // Pre conditions
        assert name != null;
        assert projects != null;
        assert projects.size() < 999;
        // For post condition
        int originalSize = projects.size();

        if (projects.size() >= 999) {
            throw new IllegalStateException("Cannot create more than 999 projects per year");
        }

        int year = LocalDate.now().getYear();
        int id =  (year - 2000) * 1000 + projects.size() + 1; // Limits the amount of projects per year to 999
        Project project = new Project(id, name);
        projects.add(project);

        assert projects.size() == originalSize + 1; // Post condition
        assert projects.contains(project); // Post condition
        return project;
    }

    public Project getProject(int id) { 
        for (Project p : projects) {
            if (p.getID() == id) return p;
        }
        return null;
    }

    public Project getProjectByName(String name) {
        for (Project p : projects) {
            if (p.getName().equals(name))
                return p;
        }
        return null;
    }

    public Employee createEmployee(String name) { // Written by BAP
        boolean empExists = employees.stream().anyMatch(e -> e.getID().equals(name));

        if(empExists) {
            return null;
        }

        Employee emp = new Employee(name);
        employees.add(emp);
        return emp;
    }

    public Employee getEmployee(String name) {
        for (Employee e : employees) {
            if (e.getID().equals(name)) return e;
        }

        // employees.remo
        return null;
    }

    public Activity createActivity(int projectId, String id, LocalDate startDate, LocalDate endDate, int budgetHalfHours, String creatorID) {
        // Pre-conditions
        assert id != null;
        assert startDate != null;
        assert endDate != null;
        assert creatorID != null;
        assert budgetHalfHours >= 0; // This assert doesnt help the CLI

        Project project = getProject(projectId);
        if (project == null) throw new IllegalArgumentException("Project does not exist");

        if (project.getActivity(id) != null) throw new IllegalArgumentException("Activity already exists!");
        if (budgetHalfHours < 0) throw new IllegalArgumentException("budget half hours is negative!");
        if (startDate.isAfter(endDate)) throw new IllegalArgumentException("Start date is after the end date!");
        Activity activity = new Activity(id, startDate, endDate, budgetHalfHours, creatorID);
        project.addActivity(activity);

        // Post-conditions
        assert activity != null;
        assert project.getActivity(id) != null;
        assert project.getActivity(id).equals(activity);

        return activity;
    }

    public void assignEmployee(int projectId, String employeeId) {
        Project project = getProject(projectId);
        if (project == null) throw new IllegalArgumentException("Project does not exist!");

        Employee user = getEmployee(employeeId);
        if (user == null) throw new IllegalArgumentException("User does not exist");

        project.assignEmployee(user);
    }
    public List<Project> getProjects() {return projects;} // Written by BAP
    public boolean deleteUser(String UserID) { // Written by BAP
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getID().equals(UserID)) {
                employees.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean assignToActivity(int projectID, String activtyID, Employee emp) { // Written by BAP
        Project project = getProject(projectID);
        if(project == null) return false;

        return project.assignToActivity(activtyID, emp);
    }
    public boolean deleteActivity(String activityID, int projectID) { // Written by BAP & assert written by MJ
        assert projectID != 0;
        Project project = getProject(projectID);
        // Pre-conditions
        List<Activity> activitiesList = project.getActivities();
        int orginalSize = activitiesList.size();
        assert activitiesList != null;
        assert activityID != null;
        assert project != null;
        for (Activity activity: activitiesList) {
            assert activity != null;
            assert activity.getID() != null;
        }

        for(int i = 0; i < activitiesList.size(); i++) {
            if(activitiesList.get(i).getID().equals(activityID)) {
                activitiesList.remove(i);

                assert activitiesList.size() == orginalSize - 1; // Post-condition
                return true;
            }
        }
        assert activitiesList.size() == orginalSize; // Post-condition
        return false;
    }

}
