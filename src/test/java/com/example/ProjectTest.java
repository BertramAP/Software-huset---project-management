package com.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProjectTest {
    Project project;
    AppHolder appHolder;
    Report projectReport;

    public ProjectTest(AppHolder appHolder) { // Written by BAP
        this.appHolder = appHolder;
    }
    @Given("there is a project with name {string}")
    public void thereIsAProjectWithName(String projectName) { // Written by BAP
        this.project = appHolder.getApp().createProject(projectName);
        appHolder.setCurrentProject(this.project);
    }

    @And("the project has no project leader")
    public void the_project_has_no_project_leader() {}

    @And("the employee {string} is assigned to the project")
    public void the_employee_is_assigned_to_the_project(String initials) {
        this.project.assignEmployee(appHolder.getCurrentEmployee());
    }

    @And("the project has an activity with name {string}")
    public void theProjectHasAnActivityWithName(String name) { // Written by AK
        LocalDate startDate = LocalDate.now();
        LocalDate end = LocalDate.of(2026, 12, 31);
        appHolder.getCurrentProject().addActivity(new Activity(name, startDate, end, 20, ""));
    }


    @When("the employee {string} is removed from the project")
    public void theEmployeeIsRemovedFromTheProject(String initials) {// Written by AK
        try {
            appHolder.getCurrentProject().removeEmployee(initials);
        } catch (Exception e) {
            appHolder.setError(e);
        }
    }

    @Then("the employee {string} is no longer assigned to the project")
    public void theEmployeeIsNoLongerAssignedToTheProject(String initials) {// Written by AK
        assertFalse(appHolder.getCurrentProject().hasEmployee(initials));
    }

    @When("the project leader requests a project report on project {string}")
    public void theProjectLeaderRequestsAProjectReport(String projectName) {
        projectReport = appHolder.getApp().getProjectByName(projectName).generateReport();
    }

    @Then("the report shows {string} total hours spent on the project")
    public void theReportShowsTotalHoursSpentOnTheProject(String hours) {
        assertEquals(hours, projectReport.getHours());
    }
    @Then("the employee {string} is the project leader of the project")
    public void theEmployeeIsTheProjectLeaderOfTheProject(String initials) {// Written by AK
        if (appHolder.getCurrentProject().getProjectLeader() == null) {
            Employee projectLeader = appHolder.getApp().createEmployee(initials);
            if(projectLeader == null) {projectLeader = appHolder.getApp().getEmployee(initials);} // If the employee exists
            System.out.println(projectLeader.getID());
            appHolder.getCurrentProject().assignProjectLeader(projectLeader);
        } else {
            assertEquals(initials, appHolder.getCurrentProject().getProjectLeader().getID());
        }
    }

    @When("the employee {string} is assigned as project leader")
    public void theEmployeeIsAssignedAsProjectLeader(String string) {// Written by AK
        try {
            appHolder.getCurrentProject().assignProjectLeader(appHolder.getCurrentEmployee());
        } catch (Exception e) {
            appHolder.setError(e);
        }
    }
    @When("the project leader requests time used on activity {string} on project {string}")
    public void theProjectLeaderRequestsTimeUsedOnActivity(String activityID, String projectName) {
        try {
            appHolder.getApp().getProjectByName(projectName).getActivity(activityID).getTimeUsed();
        } catch (Exception e) {
            appHolder.setError(new Exception("Activity does not exist"));
        }
    }

    @Then("test the report printing")
    public void testTheReportPrinting() {
        projectReport.printReport();
    }

    @Then("there is a total of {int} projects")
    public void thereIsATotalOfProjects(int num) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(appHolder.getApp().getProjects().size(), num);
    }
}
