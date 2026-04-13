package com.example;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeTest {
    Employee employee;
    @Given("there is an employee with initials {string}")
    public void there_is_an_employee_with_initials(String string) {
        // Write code here that turns the phrase above into concrete actions
        Employee employee = new Employee(string);
    }
    @When("the employee creates a project with name {string}")
    public void the_employee_creates_a_project_with_name(String string) {
        employee.createProject(string);
    }
    @Then("the project is created")
    public void the_project_is_created() {
    // Write code here that turns the phrase above into concrete actions
        assertEquals(1, employee.getProjects().size());
    }
}