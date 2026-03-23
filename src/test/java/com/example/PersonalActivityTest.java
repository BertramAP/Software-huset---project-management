package com.example;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PersonalActivityTest {
    private Employee employee;
    private String errorMessage;

    @Given("there is an employee with initials {string}")
    public void there_is_an_employee_with_initials(String initials) {
        employee = new Employee(initials);
    }

    @When("the employee {string} registers a personal activity {string} from {string} to {string}")
    public void the_employee_registers_a_personal_activity_from_to(String initials, String name, String from, String to) {
        try {
            employee.registerPersonalActivity(name, from, to);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the personal activity {string} is registered for employee {string}")
    public void the_personal_activity_is_registered_for_employee(String name, String initials) {
        assertTrue(employee.hasPersonalActivity(name));
    }

    @Then("the error message {string} is given")
    public void the_error_message_is_given(String expectedMessage) {
        assertEquals(expectedMessage, errorMessage);
    }
}
