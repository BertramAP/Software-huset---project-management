package com.example;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Employee {
    private String ID;
    private List<Project> projects;
    private List<PersonalActivity> personalActivities;

    public Employee(String ID) {
        this.ID = ID;
        this.projects = new ArrayList<Project>();
        this.personalActivities = new ArrayList<PersonalActivity>();
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public String getID() {
        return ID;
    }

    public void addPersonalActivity(String name, String from, String to) {
        LocalDate start = LocalDate.parse(from);
        LocalDate end = LocalDate.parse(to);
        personalActivities.add(new PersonalActivity(name, start, end));
    }


    public boolean hasPersonalActivity(String name) {
        return personalActivities.stream().anyMatch(a -> a.getName().equals(name));
    }

    public boolean isAvailable(int week, int year) {
        return true; // no activities assigned, always available for now
    }

}
