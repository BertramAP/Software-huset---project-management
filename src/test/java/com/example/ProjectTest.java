package com.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectTest {
    Project project;
    AppHolder appHolder;
    String projectReport;

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

    @When("the project leader requests a project report on project {string}")
    public void theProjectLeaderRequestsAProjectReport(String projectName) {
        projectReport = appHolder.getApp().getProject(projectName).generateReport();
    }

    @Then("the report shows {int} total hours spent on the project")
    public void theReportShowsTotalHoursSpentOnTheProject(int hours) {
        String expectedReport = "total time spent: " + hours + " hours";
        assertEquals(expectedReport, projectReport);
    }
    @When("the employee {string} is assigned as project leader")
    public void theEmployeeIsAssignedAsProjectLeader(String string) {
        // Write code here that turns the phrase above into concrete actions
        appHolder.getCurrentProject().assignProjectLeader(appHolder.getCurrentEmployee());
    }
}
