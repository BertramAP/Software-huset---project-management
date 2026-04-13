package com.example;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeTest {
    Employee employee;
    AppHolder appHolder;
    Project project;

    public EmployeeTest(AppHolder appHolder) { // Pico container attempt:
        this.appHolder = appHolder;
    }

    @Given("there is an employee with initials {string}")
    public void thereIsAnEmployeeWithInitials(String string) {
        // Write code here that turns the phrase above into concrete actions
        employee = new Employee(string);
    }
    @When("the employee creates a project with name {string}")
    public void theEmployeeCreatesAProjectWithName(String string) {
        App app = appHolder.getApp();
        project = app.createProject(string);
        appHolder.setApp(app);
        employee.addProject(project);
    }
    @Then("the project is created")
    public void theProjectIsCreated() {
    // Write code here that turns the phrase above into concrete actions
        assertEquals(1, employee.getProjects().size());
    }
}