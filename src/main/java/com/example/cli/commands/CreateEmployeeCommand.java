package com.example.cli.commands;

import com.example.Cli;
import com.example.Employee;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

import java.util.Scanner;

// Written by DIS
public class CreateEmployeeCommand extends AbstractCommand {

    public CreateEmployeeCommand(String usage, ProjectApp app, Cli cli) {
        super(usage, app, cli);
    }

    @Override
    public String getDescription() {
        return "Register a new employee";
    }

    @Override
    public void onCommand(Scanner scanner) {
        String name = getStringInput(scanner, "Type the initials of the employee:");
        Employee employee = app.createEmployee(name);
        System.out.println("Created employee " + name + " with ID " + employee.getID() + "\n");
    }
}
