package com.example;

public class AppHolder {
    private App app = new App();

    public AppHolder() {}
    public App getApp() {
        return this.app;
    }
    public void setApp(App app) {
        this.app = app;
    }
}
