package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    private List<Project> projects;

    public App() {
        this.projects = new ArrayList<Project>();
    }

    public Project createProject(String name) {
        Project project = new Project(name);
        projects.add(project);
        return project;
    }

    public void createUser(String name) {}

    public Project getProject(String name) { return null; }
}
