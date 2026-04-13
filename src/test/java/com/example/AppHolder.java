package com.example;

public class AppHolder {
    private App app = new App();
    private Employee currentEmployee;
    private Project currentProject;
    private Exception error;

    public AppHolder() {}

    public App getApp() { return this.app; }
    public void setApp(App app) { this.app = app; }

    public Employee getCurrentEmployee() { return this.currentEmployee; }
    public void setCurrentEmployee(Employee e) { this.currentEmployee = e; }

    public Project getCurrentProject() { return this.currentProject; }
    public void setCurrentProject(Project p) { this.currentProject = p; }

    public Exception getError() { return this.error; }
    public void setError(Exception e) { this.error = e; }
}
