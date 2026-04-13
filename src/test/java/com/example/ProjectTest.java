package com.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
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
        appHolder.setCurrentProject(this.project);
    }

    @And("the project has no project leader")
    public void the_project_has_no_project_leader() {}

    @And("the employee {string} is assigned to the project")
    public void the_employee_is_assigned_to_the_project(String initials) {
        this.project.assignEmploye(appHolder.getCurrentEmployee());
    }

    @Given("the project has an activity with name {string}")
    public void theProjectHasAnActivityWithName(String name) {
        LocalDate startDate = LocalDate.now();
        LocalDate end = LocalDate.of(2026, 12, 31);
        this.project.addActivity(new Activity(name, startDate, end, 20, ""));
    }

    @And("the employee {string} is the project leader of the project")
    public void the_employee_is_the_project_leader_of_the_project(String initials) {
        this.project.assignProjectLeader(appHolder.getCurrentEmployee());
    }
}
