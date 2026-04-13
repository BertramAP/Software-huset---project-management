package com.example;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PersonalActivityTest {
    private Employee employee;
    private String errorMessage;

    
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
}
