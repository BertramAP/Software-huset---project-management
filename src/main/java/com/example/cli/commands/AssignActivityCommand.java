package com.example.cli.commands;

import com.example.*;
import com.example.cli.AbstractCommand;

import java.util.Scanner;
import java.util.stream.Collectors;

// Written by DIS
public class AssignActivityCommand extends AbstractCommand {

    public AssignActivityCommand(String usage, ProjectApp app, Cli cli) {
        super(usage, app, cli);
    }

    @Override
    public String getDescription() {
        return "Assigns an employee to an activity in a project";
    }

    @Override
    public void onCommand(Scanner scanner) {
        int projectId = promptForProjectId(scanner);
        Project project = app.getProject(projectId);
        if (project == null)
            throw new IllegalArgumentException("Project does not exist!");

        System.out.println("Select an activity:");
        String activites = project.getActivities().stream().map(activity -> activity.getID() + " ").collect(Collectors.joining());
        System.out.println(activites);
        String activityName = getStringInput(scanner);

        String username = getStringInput(scanner, "Which employee should be assigned?");

        Employee employee = app.getEmployee(username);
        if (employee == null)
            throw new IllegalArgumentException("Employee does not exist!");

        if (!app.assignToActivity(projectId, activityName, employee))
            throw new RuntimeException("An error occurred");
        System.out.println("Assigned " + employee.getID() + " to " + activityName);
    }
}
