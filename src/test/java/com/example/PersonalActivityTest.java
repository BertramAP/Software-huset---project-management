package com.example;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PersonalActivityTest {
    AppHolder appHolder;

    public PersonalActivityTest(AppHolder appHolder) { // Written by AK
        this.appHolder = appHolder;
    }

    @When("the employee {string} registers a personal activity {string} from {string} to {string}")
    public void the_employee_registers_a_personal_activity_from_to(String initials, String name, String from, String to) { // Written by OFK
        try {
            appHolder.getCurrentEmployee().addPersonalActivity(name, from, to);
        } catch (Exception e) {
            appHolder.setError(e);
        }
    }

    @Then("the personal activity {string} is registered for employee {string}")
    public void the_personal_activity_is_registered_for_employee(String name, String initials) { // Written by AK
        assertTrue(appHolder.getCurrentEmployee().hasPersonalActivity(name));
    }
}
