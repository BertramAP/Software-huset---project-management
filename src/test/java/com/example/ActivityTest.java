package com.example;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActivityTest {
    AppHolder appHolder;

    public ActivityTest(AppHolder appHolder) { // Written by AK
        this.appHolder = appHolder;
    }

    @When("the employee {string} creates an activity with name {string}")
    public void the_employee_creates_an_activity_with_name(String initials, String name) { // Written by AK
        appHolder.getCurrentProject().addActivity(new Activity(name, null, null, 0, initials));
    }

    @Then("the activity {string} is added to the project")
    public void the_activity_is_added_to_the_project(String name) {// Written by AK
        assertNotNull(appHolder.getCurrentProject().getActivity(name));
    }

    @When("the project leader creates an activity with name {string}")
    public void the_project_leader_creates_an_activity_with_name(String name) {// Written by AK
        try {
            appHolder.getCurrentProject().addActivity(new Activity(name, null, null, 0, appHolder.getCurrentEmployee().getID()));
        } catch (Exception e) {
            appHolder.setError(e);
        }
    }

    @When("the employee {string} is added to activity {string}")
    public void the_employee_is_added_to_activity(String initials, String activityName) {// Written by AK
        try {
            appHolder.getCurrentProject().getActivity(activityName).addEmployee(appHolder.getCurrentEmployee());
        } catch (Exception e) {
            appHolder.setError(e);
        }
    }

    @Then("employee is added to activity {string}")
    public void employee_is_added_to_activity(String activityName) {// Written by AK
        assertTrue(appHolder.getCurrentProject().getActivity(activityName).hasEmployee(appHolder.getCurrentEmployee().getID()));
    }

    @And("the employee {string} is assigned to the activity {string}")
    public void the_employee_is_assigned_to_the_activity(String initials, String activityName) { // Written by AK
        appHolder.getCurrentProject().getActivity(activityName).addEmployee(appHolder.getCurrentEmployee());
    }
}
