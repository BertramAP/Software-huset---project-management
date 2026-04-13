package com.example;

import io.cucumber.java.PendingException;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;

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

    @Then("the report shows {string} total hours spent on the project")
    public void theReportShowsTotalHoursSpentOnTheProject(String hours) {
        String expectedReport = "total time spent: " + hours + " hours";
        assertEquals(expectedReport, projectReport);
    }

    @When("the project leader requests time used on activity {string} on project {string}")
    public void theProjectLeaderRequestsTimeUsedOnActivity(String activityID, String projectID) {
        try {
            System.out.println(appHolder.getApp().getProject(projectID).getActivity(activityID));
            appHolder.getApp().getProject(projectID).getActivity(activityID).getTimeUsed();
        } catch (Exception e) {
            appHolder.setError(new Exception("Activity does not exist"));
        }
    }
}
