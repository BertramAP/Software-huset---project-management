package com.example;

public class AppHolder {
    private ProjectApp app = new ProjectApp();
    private Employee currentEmployee;
    private Project currentProject;
    private Exception error;

    public AppHolder() {}

    public ProjectApp getApp() { return this.app; }
    public void setApp(ProjectApp app) { this.app = app; }

    public Employee getCurrentEmployee() { return this.currentEmployee; }
    public void setCurrentEmployee(Employee e) { this.currentEmployee = e; }

    public Project getCurrentProject() { return this.currentProject; }
    public void setCurrentProject(Project p) { this.currentProject = p; }

    public Exception getError() { return this.error; }
    public void setError(Exception e) { this.error = e; }
}
