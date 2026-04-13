package com.example;

import com.example.errors.DuplicateProjectNameException;

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

    public void createUser(String name) {}

    public Project getProject(String name) {
        for (Project project: projects) {
            if (project.getID().equals(name)) return project;
        }
        return null;
    }
}
