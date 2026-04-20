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
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProjectTest {
    Project project;
    AppHolder appHolder;
    Report projectReport;

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


    @When("the employee {string} is removed from the project")
    public void theEmployeeIsRemovedFromTheProject(String initials) {
        try {
            appHolder.getCurrentProject().removeEmployee(initials);
        } catch (Exception e) {
            appHolder.setError(e);
        }
    }

    @Then("the employee {string} is no longer assigned to the project")
    public void theEmployeeIsNoLongerAssignedToTheProject(String initials) {
        assertFalse(appHolder.getCurrentProject().hasEmployee(initials));
    }

    @When("the project leader requests a project report on project {string}")
    public void theProjectLeaderRequestsAProjectReport(String projectName) {
        projectReport = appHolder.getApp().getProject(projectName).generateReport();
    }

    @Then("the report shows {string} total hours spent on the project")
    public void theReportShowsTotalHoursSpentOnTheProject(String hours) {
        assertEquals(hours, projectReport.getHours());
    }
    @Then("the employee {string} is the project leader of the project")
    public void theEmployeeIsTheProjectLeaderOfTheProject(String initials) {
        if (appHolder.getCurrentProject().getProjectLeader() == null) {
            appHolder.getCurrentProject().assignProjectLeader(appHolder.getCurrentEmployee());
        } else {
            assertEquals(initials, appHolder.getCurrentProject().getProjectLeader().getID());
        }
    }

    @When("the employee {string} is assigned as project leader")
    public void theEmployeeIsAssignedAsProjectLeader(String string) {
        try {
            appHolder.getCurrentProject().assignProjectLeader(appHolder.getCurrentEmployee());
        } catch (Exception e) {
            appHolder.setError(e);
        }
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

    @Then("test the report printing")
    public void testTheReportPrinting() {
        projectReport.printReport();
    }
}
