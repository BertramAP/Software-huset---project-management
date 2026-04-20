package com.example.cli.commands;

import com.example.Cli;
import com.example.Employee;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

public class AssignCommand extends AbstractCommand {

    public AssignCommand(ProjectApp app, Cli cli) {
        super(app, cli);
    }

    @Override
    public String getUsage() {
        return "assign <project-name> <activity-name> <username>";
    }

    @Override
    public boolean onCommand(String[] args) {
        if (!args[0].equals("assign"))
            return false;
        if (args.length < 3)
            throw new IllegalArgumentException("Usage: " + getUsage());

        Employee employee = app.getUser(args[3]);
        if (employee == null)
            throw new IllegalArgumentException("User does not exist!");

        if (!app.assignToActivity(args[1], args[2], employee))
            throw new RuntimeException("An error ocurred");
        System.out.println("Assigned " + employee.getID() + " to " + args[2]);
        return true;
    }
}
