package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NameNotFoundException;

import com.example.errors.DuplicateProjectNameException;

public class ProjectApp {
    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();

    private Boolean doesProjectExist(String name) {
        for (Project project: this.projects) {
            if (project.getID().equals(name)) return true;
        }
        return false;
    }

    public Project createProject(String name) throws DuplicateProjectNameException {
        Project project = new Project(name);
        if (doesProjectExist(name)) {
            throw new DuplicateProjectNameException("Project name already exists");
        }
        projects.add(project);
        return project;
    }

    public Project getProject(String name) { 
        for (Project p : projects) {
            if (p.getID().equals(name)) return p;
        }

        return null;
    }

    public Employee createUser(String name) {
        Employee user = new Employee(name);
        employees.add(user);
        return user;
    }

    public Employee getUser(String name) {
        for (Employee e : employees) {
            if (e.getID().equals(name)) return e;
        }

        // employees.remo
        return null;
    }

    public void assignEmployee(String projectId, String employeeId) {
        Project project = getProject(projectId);
        if (project == null) throw new IllegalArgumentException("Project does not exist!");

        Employee user = getUser(employeeId);
        if (user == null) throw new IllegalArgumentException("User does not exist");

        project.assignEmploye(user);
    }
}
