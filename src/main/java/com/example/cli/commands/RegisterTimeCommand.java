package com.example.cli.commands;

import java.time.LocalDate;
import java.util.Scanner;

import com.example.*;
import com.example.cli.AbstractCommand;

// Written by DIS
public class RegisterTimeCommand extends AbstractCommand {

    public RegisterTimeCommand(String usage, ProjectApp app, Cli cli) {
        super(usage, app, cli);
    }

    @Override
    public String getDescription() {
        return "Register how many half-hours you've spent on an activity";
    }

    @Override
    public void onCommand(Scanner scanner) {
        int projectId = promptForProjectId(scanner);
        Project project = app.getProject(projectId);
        if (project == null)
            throw new IllegalArgumentException("Project does not exist!");

        String activityName = getStringInput(scanner, "Select an activity:");
        Activity activity = project.getActivity(activityName);
        if (activity == null)
            throw new IllegalArgumentException("Activity does not exist!");

        // Add user to the project/activity if they aren't already (Written by MJ)
        if (!project.employees.contains( cli.getCurrentUser() ))
            project.assignEmployee(cli.getCurrentUser());
        if (!activity.hasEmployee(cli.getCurrentUser().getID()))
            project.assignToActivity(activity.getID(), cli.getCurrentUser());

        int halfHours = getIntegerInput(scanner, "How many half hours have you completed on this task?");
        activity.addContribution(
                new Contribution(cli.getCurrentUser(), halfHours, LocalDate.now()));

        System.out.println("Successfully registered " + halfHours + " half hours to " + activity.getID());
    }
}
