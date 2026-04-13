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
        this.project = appHolder.getApp().createProject(projectName);
    }
    @Given("the project has an activity with name {string}")
    public void theProjectHasAnActivityWithName(String string) {
        // Write code here that turns the phrase above into concrete actions

    }

}
