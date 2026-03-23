package com.example;
import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String ID;
    private List<Project> projects;

    public Employee(String ID) {
        this.ID = ID;
        this.projects = new ArrayList<Project>();
    }
    public void createProject(String name) {
        Project project = new Project(name);
        projects.add(project);
    }
    public List<Project> getProjects() {
        return projects;
    }
}
