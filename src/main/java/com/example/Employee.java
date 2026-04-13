package com.example;
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

    public void registerPersonalActivity(String name, String from, String to) {
        String[] fromParts = from.split("-");
        String[] toParts = to.split("-");
        Calendar startDate = Calendar.getInstance();
        startDate.set(Integer.parseInt(fromParts[0]), Integer.parseInt(fromParts[1]) - 1, Integer.parseInt(fromParts[2]));
        Calendar endDate = Calendar.getInstance();
        endDate.set(Integer.parseInt(toParts[0]), Integer.parseInt(toParts[1]) - 1, Integer.parseInt(toParts[2]));
        personalActivities.add(new PersonalActivity(name, startDate, endDate));
    }

    public boolean hasPersonalActivity(String name) {
        return personalActivities.stream().anyMatch(a -> a.getName().equals(name));
    }
}
