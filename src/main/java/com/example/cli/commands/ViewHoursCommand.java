package com.example.cli.commands;

import com.example.Cli;
import com.example.Employee;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

import java.time.LocalDate;
import java.util.Scanner;

public class ViewHoursCommand extends AbstractCommand {
    public ViewHoursCommand(String usage, ProjectApp app, Cli cli) {
        super(usage, app, cli);
    }

    @Override
    public String getDescription() {
        return "Prints the total hours a user has registered on a specific date";
    }

    @Override
    public void onCommand(Scanner scanner) {
        Employee employee = app.getEmployee(getStringInput(scanner, "Type a username:"));
        if (employee == null)
            throw new IllegalArgumentException("User does not exist!");
        String date = getStringInput(scanner, "Which date do you want to check? (yyyy-mm-dd)");
        LocalDate localDate = LocalDate.parse(date);
        int hours = employee.viewHours(localDate);
        System.out.println("Hours registered: " + hours);
    }
}