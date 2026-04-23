package com.example.cli.commands;

import com.example.Cli;
import com.example.Employee;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

public class AssignActivityCommand extends AbstractCommand {

    public AssignActivityCommand(ProjectApp app, Cli cli) {
        super(app, cli);
    }

    @Override
    public String getUsage() {
        return "assign-activity <project-id> <activity-name> <username>";
    }

    @Override
    public String getDescription() {
        return "Assigns a user to an activity in a project";
    }

    @Override
    public boolean onCommand(String[] args) {
        if (!args[0].equals("assign-activity"))
            return false;
        if (args.length < 3)
            throw new IllegalArgumentException("Usage: " + getUsage());

        Employee employee = app.getEmployee(args[3]);
        if (employee == null)
            throw new IllegalArgumentException("User does not exist!");

        int projectId = Integer.parseInt(args[1]);
        if (!app.assignToActivity(projectId, args[2], employee))
            throw new RuntimeException("An error ocurred");
        System.out.println("Assigned " + employee.getID() + " to " + args[2]);
        return true;
    }
}
