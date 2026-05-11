package com.example.cli;

import com.example.Cli;
import com.example.Project;
import com.example.ProjectApp;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

// Written by DIS
public abstract class AbstractCommand {
    protected ProjectApp app;
    protected Cli cli;
    protected String usage;

    public AbstractCommand(String usage, ProjectApp app, Cli cli) {
        this.app = app;
        this.cli = cli;
        this.usage = usage;
    }

    abstract public void onCommand(Scanner scanner);
    public String getUsage() { return usage; }
    abstract public String getDescription();

    protected int getIntegerInput(Scanner scanner, String prompt, String description) {
        System.out.println(prompt);
        return getIntegerInput(scanner, description);
    }
    protected int getIntegerInput(Scanner scanner, String prompt) {
        System.out.println(prompt);
        return getIntegerInput(scanner);
    }
    protected int getIntegerInput(Scanner scanner) {
        System.out.print("> ");
        return Integer.parseInt(scanner.nextLine());
    }

    protected String getStringInput(Scanner scanner, String prompt) {
        System.out.println(prompt);
        return getStringInput(scanner);
    }
    protected String getStringInput(Scanner scanner) {
        System.out.print("> ");
        return scanner.nextLine();
    }

    protected int promptForProjectId(Scanner scanner) {
        return getIntegerInput(scanner, "Choose a project ID:", app.getProjects().stream().map(project -> project.getID() + " ").collect(Collectors.joining()));
    }

    protected LocalDate firstDayOfWeek(int weekNumber) {
        return LocalDate.now().with(WeekFields.of(Locale.getDefault()).weekOfYear(), weekNumber).with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1);
    }
}
