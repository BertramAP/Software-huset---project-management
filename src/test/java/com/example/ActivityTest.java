package com.example;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ActivityTest {
    Project project;
    Employee employee;
    Activity currentActivity;
    String errorMessage;
    AppHolder appHolder;

    public ActivityTest(AppHolder appHolder) {
        this.appHolder = appHolder;
    }

    @And("the project has no project leader")
    public void the_project_has_no_project_leader() {}

    @And("the employee {string} is assigned to the project")
    public void the_employee_is_assigned_to_the_project(String initials) {
        employee = new Employee(initials);
        project.assignEmploye(employee);
    }

    @When("the employee {string} creates an activity with name {string}")
    public void the_employee_creates_an_activity_with_name(String initials, String name) {
        project.addActivity(new Activity(name, null, null, 0, initials));
    }

    @Then("the activity {string} is added to the project")
    public void the_activity_is_added_to_the_project(String name) {
        assertNotNull(project.getActivity(name));
    }
    /*
    @And("the project has an activity with name {string}")
    public void the_project_has_an_activity_with_name(String name) {
        Activity a = new Activity(name, null, null, 0, "");
        project.addActivity(a);
        this.currentActivity = a;
    }*/

    @And("the employee {string} is the project leader of the project")
    public void the_employee_is_the_project_leader_of_the_project(String initials) {
        employee = new Employee(initials);
        project.assignProjectLeader(employee);
    }

    @When("the project leader creates an activity with name {string}")
    public void the_project_leader_creates_an_activity_with_name(String name) {
        try {
            if (project.getActivity(name) != null) throw new Exception("Activity already exists");
            project.addActivity(new Activity(name, null, null, 0, employee.getID()));
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }
    /*
    @Then("the error message {string} is given")
    public void the_error_message_is_given(String expected) {
        assertEquals(expected, errorMessage);
    }*/
    
    @When("the employee {string} is added to activity {string}")
    public void the_employee_is_added_to_activity(String initials, String activityName) {
        try {
            Employee emp = appHolder.getCurrentEmployee(); 
            Activity a = project.getActivity(activityName);
            if (a.hasEmployee(emp.getID())) throw new Exception("Employee is already assigned to the activity");
            a.addEmployee(emp);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("employee is added to activity {string}")
    public void employee_is_added_to_activity(String activityName) {
        Employee emp = appHolder.getCurrentEmployee();
        assertTrue(project.getActivity(activityName).hasEmployee(emp.getID()));
    }

    @And("the employee {string} is assigned to the activity {string}")
    public void the_employee_is_assigned_to_the_activity(String initials, String activityName) {
        Employee emp = appHolder.getCurrentEmployee();
        project.getActivity(activityName).addEmployee(emp);
    }
}
