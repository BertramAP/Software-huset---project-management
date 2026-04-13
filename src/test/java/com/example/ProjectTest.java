package com.example;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en_old.Ac;

import java.time.LocalDate;

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
        LocalDate startDate = LocalDate.now();

        LocalDate end = LocalDate.of(2026, 12, 31);


        Activity activity = new Activity(string, startDate, end, 20, "");

        this.project.addActivity(activity);

    }

}
