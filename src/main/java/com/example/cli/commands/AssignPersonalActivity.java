package com.example.cli.commands;

import com.example.Cli;
import com.example.Employee;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

import java.util.Scanner;

// Written by DIS
public class AssignPersonalActivity extends AbstractCommand {

    public AssignPersonalActivity(String usage, ProjectApp app, Cli cli) {
        super(usage, app, cli);
    }

    @Override
    public void onCommand(Scanner scanner) {
        String name = getStringInput(scanner, "Choose a name for the activity:");
        String startWeek = getStringInput(scanner, "Which date does the activity start? (yyyy-mm-dd)");
        String endWeek = getStringInput(scanner, "Which date does the activity end? (yyyy-mm-dd)");
        String username = getStringInput(scanner, "Which employee should this be assigned to? (empty for current employee)");

        Employee currentEmployee = username.isEmpty() ? cli.getCurrentUser() : app.getEmployee(username);
        if (currentEmployee == null) throw new IllegalArgumentException("Employee does not exist!");

        currentEmployee.addPersonalActivity(name, startWeek, endWeek);
        System.out.println("Created personal activity " + name);
    }

    @Override
    public String getDescription() {
        return "Create and assign a personal activity to an employee";
    }
}
