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
        String name;
        do {
            name = getStringInput(scanner, "Type the initials of the employee:").trim();
            if (name.isEmpty()) System.out.println("Initials cannot be empty.");
        } while (name.isEmpty());
        Employee employee = app.createEmployee(name);
        if (employee == null) {
            throw new IllegalArgumentException("Employee already exists!");
        }
        System.out.println("Created employee " + name + " with ID " + employee.getID());
    }
}
