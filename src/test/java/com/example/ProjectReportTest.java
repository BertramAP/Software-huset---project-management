package com.example;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectReportTest {
    String projectName;
    Project project;
    App app; // TODO: Init this somehow?

    /*@Given("there is a project with name {string}")
    public void there_is_a_project_with_name(String projectName) {
        this.projectName = projectName;
        app.createProject(projectName);
    }*/

    @And("the employee {string} is the project leader of the project")
    public void the_employee_is_the_project_leader_of_the_project(String name) {
        app.createUser(name);
        this.project = app.getProject(projectName);
        this.project.assignProjectLeader(project.getUser(name));
    }

    @And("the project has an activity with name {string}")
    public void theProjectHasAnActivityWithName(String activityName) {
        this.project.addActivity(new Activity(activityName)); // TODO: Implement activity class
    }

    @And("the employee {string} has registered {int} hours on activity {string} on date {string}")
    public void theEmployeeHasRegisteredHoursOnActivityOnDate(String employeeName, int hours, String activityName, String date) {

    }

    @When("the project leader requests a project report")
    public void theProjectLeaderRequestsAProjectReport() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("the report shows {int} total hours spent on the project")
    public void theReportShowsTotalHoursSpentOnTheProject(int arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("the project leader requests time used on activity {string}")
    public void theProjectLeaderRequestsTimeUsedOnActivity(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


}