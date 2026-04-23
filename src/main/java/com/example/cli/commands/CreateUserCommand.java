package com.example.cli.commands;

import com.example.Cli;
import com.example.Employee;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

public class CreateUserCommand extends AbstractCommand {

    public CreateUserCommand(ProjectApp app, Cli cli) {
        super(app, cli);
    }

    @Override
    public String getUsage() {
        return "create-user <name>";
    }

    @Override
    public String getDescription() {
        return "Register a new user";
    }

    @Override
    public boolean onCommand(String[] args) {
        if (!args[0].equals("create-user"))
            return false;
        if (args.length < 2)
            throw new IllegalArgumentException("Usage: " + getUsage());

        Employee user = app.createUser(args[1]);
        System.out.println("Created user " + args[1] + " with ID " + user.getID());
        return true;
    }
}
