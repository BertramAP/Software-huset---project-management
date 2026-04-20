package com.example;

import static org.junit.jupiter.api.Assertions.*;

import com.example.errors.DuplicateProjectNameException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.LocalDate;

public class EmployeeTest {
    Employee employee;
    AppHolder appHolder;
    Project project;

    public EmployeeTest(AppHolder appHolder) {
        this.appHolder = appHolder;
    }

    @Given("there is an employee with initials {string}")
    public void thereIsAnEmployeeWithInitials(String string) {
        this.employee = new Employee(string);
        appHolder.setCurrentEmployee(this.employee);
    }

    @When("the employee creates a project with name {string}")
    public void theEmployeeCreatesAProjectWithName(String string) {
        App app = appHolder.getApp();
        try {
            project = app.createProject(string);
        } catch (DuplicateProjectNameException e) {
            appHolder.setError(e);
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

    @Then("the employee {string} is not listed as available")
    public void the_employee_is_not_listed_as_available(String initials) {
        assertFalse(employee.isAvailable(checkedWeek, checkedYear));
    }

    @And("the employee {string} is assigned to an activity in week {int} of {int}")
    public void the_employee_is_assigned_to_an_activity_in_week_of(String initials, int week, int year) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDate date = LocalDate.of(year, 6, 1).with(weekFields.weekOfWeekBasedYear(), week);
        Activity activity = new Activity("test", date, date, 10, initials);
        employee.assignToActivity(activity);
    }



    @Then("the error message {string} is given")
    public void theErrorMessageIsGiven(String errorMessage) {
        assertEquals(appHolder.getError().getMessage(), errorMessage);
    }

    @And("the employee {string} has registered {int} hours on activity {string} under project {string} on date {string}")
    public void theEmployeeHasRegisteredHoursOnActivityOnDate(String employeeID, int hours, String activityName, String projectName, String date) {
        // Write code here that turns the phrase above into concrete actions

        Employee employee = new Employee(employeeID); // Create the employee
        String[] splitString = date.split("-");
        LocalDate localDate = LocalDate.of(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[1]), Integer.parseInt(splitString[2]));
        Contribution contribution = new Contribution(employee, hours*2, localDate); // Create the contribution

        appHolder.getApp().getProject(projectName).getActivity(activityName).addContribution(employeeID, contribution);
    }

    @And("the employee {string} has registered {int} hours on activity {string} on date {string}")
    public void theEmployeeHasRegisteredHoursOnActivityOnDate(
            String employeeID, int hours, String activityName, String date) {
        LocalDate localDate = LocalDate.parse(date);
        Project project = appHolder.getCurrentProject();

        Activity activity = project.getActivity(activityName);
        if (activity == null) {
            activity = new Activity(activityName, localDate, localDate, hours * 2, employeeID);
            project.addActivity(activity);
        }

        Employee employee = appHolder.getCurrentEmployee();
        activity.addContribution(employeeID, new Contribution(employee, hours * 2, localDate));
    }

    private int totalRegisteredHours;

    @When("the employee {string} views registered hours for date {string}")
    public void theEmployeeViewsRegisteredHoursForDate(String employeeID, String date) {
        LocalDate target = LocalDate.parse(date);
        totalRegisteredHours = 0;
        for (Project p : appHolder.getApp().getProjects())
            for (Activity a : p.getActivities()) {
                Contribution c = a.getContribution(employeeID);
                if (c != null && c.getDate().equals(target))
                    totalRegisteredHours += c.getWorkTime() / 2;
            }
    }

    @Then("the total registered hours are {int}")
    public void theTotalRegisteredHoursAre(int expected) {
        assertEquals(expected, totalRegisteredHours);
    }

    @And("the employee {string} changes the registered hours to {int} on activity {string} on date {string}")
    public void theEmployeeChangesTheRegisteredHoursToOnActivityOnDate(String employeeID, int hours, String activityName, String date) {
        appHolder.getCurrentProject().getActivity(activityName).getContribution(employeeID).updateTime(hours * 2);
    }

    @Then("{int} hours are registered for employee {string} on activity {string} on date {string}")
    public void hoursAreRegisteredForEmployeeOnActivityOnDate(int hours, String employeeID, String activityName, String date) {
        assertEquals(hours * 2, appHolder.getCurrentProject().getActivity(activityName).getTimeUsed(employeeID));
    }

    @When("the employee {string} registers {int} hours on activity {string} on date {string}")
    public void theEmployeeRegistersHoursOnActivityOnDate(String employeeID, int hours, String activityName, String date) {
        try {
            appHolder.getCurrentProject().getActivity(activityName).addContribution(
                    employeeID,
                    new Contribution(appHolder.getCurrentEmployee(), hours * 2, LocalDate.parse(date)));
        } catch (Exception e) {
            appHolder.setError(e);
            }
    }



}