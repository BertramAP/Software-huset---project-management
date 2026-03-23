package com.example;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectReportTest {
    @Given("there is a project with name {string}")
    public void there_is_a_project_with_name(String name) {
        throw new PendingException("idfk");
    }

    @And("the employee {string} is the project leader of the project")
    public void the_employee_is_the_project_leader_of_the_project(String name) {
        throw new PendingException("idfk");
    }
}