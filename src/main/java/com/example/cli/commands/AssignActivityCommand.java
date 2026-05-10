package com.example.cli.commands;

import com.example.Cli;
import com.example.Employee;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

import java.util.Scanner;

// Written by DIS
public class AssignActivityCommand extends AbstractCommand {

    public AssignActivityCommand(String usage, ProjectApp app, Cli cli) {
        super(usage, app, cli);
    }

    @Override
    public String getDescription() {
        return "Assigns a user to an activity in a project";
    }

    @Override
    public void onCommand(Scanner scanner) {
        int projectId = promptForProjectId(scanner);
        String activityName = getStringInput(scanner, "Choose an activity:");
        String username = getStringInput(scanner, "Which employee should be assigned?");

        Employee employee = app.getEmployee(username);
        if (employee == null)
            throw new IllegalArgumentException("User does not exist!");

        if (!app.assignToActivity(projectId, activityName, employee))
            throw new RuntimeException("An error occurred");
        System.out.println("Assigned " + employee.getID() + " to " + activityName + "\n");
    }
}
