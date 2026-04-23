package com.example.cli.commands;

import java.time.LocalDate;

import com.example.Cli;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

public class CreateActivityCommand extends AbstractCommand {

    public CreateActivityCommand(ProjectApp app, Cli cli) {
        super(app, cli);
    }

    @Override
    public String getUsage() {
        return "create-activity <activity-name> <project-name> <start-date> <end-date> <half-hours>";
    }

    @Override
    public String getDescription() {
        return "Create an activity in a project";
    }

    @Override
    public boolean onCommand(String[] args) {
        if (!args[0].equals("create-activity"))
            return false;
        if (args.length < 6)
            throw new IllegalArgumentException("Usage: " + getUsage());

        app.createActivity(args[2], args[1], LocalDate.parse(args[3]), LocalDate.parse(args[4]),
                Integer.parseInt(args[5]), cli.getCurrentUser().getID());

        System.out.println("Created activity " + args[1]);
        return true;
    }
}
