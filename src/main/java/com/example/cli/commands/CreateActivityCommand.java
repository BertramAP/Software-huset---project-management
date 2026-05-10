package com.example.cli.commands;

import java.util.Scanner;

import com.example.Cli;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

// Written by DIS
public class CreateActivityCommand extends AbstractCommand {

    public CreateActivityCommand(String usage, ProjectApp app, Cli cli) {
        super(usage, app, cli);
    }

    @Override
    public String getDescription() {
        return "Create an activity in a project";
    }

    @Override
    public void onCommand(Scanner scanner) {
        String name = getStringInput(scanner, "Choose a name for the activity:");
        int projectId = promptForProjectId(scanner);
        int startWeek = getIntegerInput(scanner, "Which week does the activity start?");
        int endWeek = getIntegerInput(scanner, "Which week does the activity end?");
        int halfHours = getIntegerInput(scanner, "How many half hours does the activity require?");

        app.createActivity(projectId, name, firstDayOfWeek(startWeek), firstDayOfWeek(endWeek), halfHours, cli.getCurrentUser().getID());

        System.out.println("Created activity " + name);
    }
}
