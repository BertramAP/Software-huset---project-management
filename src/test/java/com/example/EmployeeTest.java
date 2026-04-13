package com.example;

import static org.junit.jupiter.api.Assertions.*;

import com.example.errors.DuplicateProjectNameException;
import io.cucumber.java.PendingException;
import java.util.Calendar;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeTest {
    Employee employee;
    AppHolder appHolder;
    Project project;
    Exception errorHolder;

    public EmployeeTest(AppHolder appHolder) { // Pico container attempt:
        this.appHolder = appHolder;
    }

    @Given("there is an employee with initials {string}")
    public void thereIsAnEmployeeWithInitials(String string) {
        // Write code here that turns the phrase above into concrete actions
        this.employee = new Employee(string);
    }
    @When("the employee creates a project with name {string}")
    public void theEmployeeCreatesAProjectWithName(String string) {
        App app = appHolder.getApp();
        // Catch the error and save it to be used in other step
        try {
            project = app.createProject(string);
        } catch (DuplicateProjectNameException e) {
            errorHolder = e;
        }
        appHolder.setApp(app);
        employee.addProject(project);
    }
    @Then("the project is created")
    public void theProjectIsCreated() {
    // Write code here that turns the phrase above into concrete actions
        assertEquals(1, employee.getProjects().size());
    }

    @And("the employee {string} has no activities in week {int} of {int}")
        public void the_employee_has_no_activities_in_week_of(String initials, int week, int year) {
        // New employee has no activities by default, nothing to set up
    }

    int checkedWeek;
    int checkedYear;

    @When("the project leader checks availability for week {int} of {int}")
    public void the_project_leader_checks_availability_for_week_of(int week, int year) {
        this.checkedWeek = week;
        this.checkedYear = year;
    }

    @Then("the employee {string} is listed as available")
    public void the_employee_is_listed_as_available(String initials) {
        assertTrue(employee.isAvailable(checkedWeek, checkedYear));
    }

    @And("the employee {string} is assigned to an activity in week {int} of {int}")
    public void the_employee_is_assigned_to_an_activity_in_week_of(String initials, int week, int year) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.WEEK_OF_YEAR, week);
        Activity activity = new Activity("test", date, date, 10, initials);
        employee.assignToActivity(activity);
    }



    @Then("the error message {string} is given")
    public void theErrorMessageIsGiven(String errorMessage) {
        assertEquals(errorHolder.getMessage(), errorMessage);
    }
}