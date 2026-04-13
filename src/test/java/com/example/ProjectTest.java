package com.example;

import io.cucumber.java.en.Given;

public class ProjectTest {
    Project project;
    AppHolder appHolder;

    public ProjectTest(AppHolder appHolder) {
        this.appHolder = appHolder;
    }

    @Given("there is a project with name {string}")
    public void thereIsAProjectWithName(String projectName) {
        appHolder.getApp().createProject(projectName);
    }
}
