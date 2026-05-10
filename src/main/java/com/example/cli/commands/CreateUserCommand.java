package com.example.cli.commands;

import com.example.Cli;
import com.example.Employee;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

import java.util.Scanner;

// Written by DIS
public class CreateUserCommand extends AbstractCommand {

    public CreateUserCommand(String usage, ProjectApp app, Cli cli) {
        super(usage, app, cli);
    }

    @Override
    public String getDescription() {
        return "Register a new user";
    }

    @Override
    public void onCommand(Scanner scanner) {
        String name = getStringInput(scanner, "Type the initials of the user:");
        Employee user = app.createEmployee(name);
        System.out.println("Created user " + name + " with ID " + user.getID());
    }
}
