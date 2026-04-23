package com.example.cli.commands;

import java.time.LocalDate;

import com.example.Activity;
import com.example.Cli;
import com.example.Contribution;
import com.example.Project;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

public class RegisterTimeCommand extends AbstractCommand {

    public RegisterTimeCommand(ProjectApp app, Cli cli) {
        super(app, cli);
    }

    @Override
    public String getUsage() {
        return "register-time <project-name> <activity-name> <half-hours>";
    }

    @Override
    public String getDescription() {
        return "Register how many half-hours you've spent on an activity";
    }

    @Override
    public boolean onCommand(String[] args) {
        if (!args[0].equals("register-time"))
            return false;
        if (args.length < 4)
            throw new IllegalArgumentException("Usage: " + getUsage());

        Project project = app.getProject(args[1]);
        if (project == null)
            throw new IllegalArgumentException("Project does not exist!");

        Activity activity = project.getActivity(args[2]);
        if (activity == null)
            throw new IllegalArgumentException("Activity does not exist!");

        int halfHours = Integer.parseInt(args[3]);
        activity.addContribution(
                new Contribution(cli.getCurrentUser(), halfHours, LocalDate.now()));

        System.out.println("Successfully registered " + halfHours + " half hours to " + activity.getID());
        return true;
    }
}
