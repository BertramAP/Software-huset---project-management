package com.example;

public class AppHolder {
    private App app = new App();
    private Employee currentEmployee;

    public AppHolder() {}
    public App getApp() {
        return this.app;
    }
    public void setApp(App app) {
        this.app = app;
    }
    public Employee getCurrentEmployee() {
        return this.currentEmployee;
    }
    public void setCurrentEmployee(Employee emp) {
        this.currentEmployee = emp;
    }
}
