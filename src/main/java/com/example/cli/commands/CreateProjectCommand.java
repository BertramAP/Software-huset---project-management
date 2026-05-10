package com.example.cli.commands;

import com.example.Cli;
import com.example.Project;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

import java.util.Scanner;

// Written by DIS
public class CreateProjectCommand extends AbstractCommand {

    public CreateProjectCommand(String usage, ProjectApp app, Cli cli) {
        super(usage, app, cli);
    }

    @Override
    public String getDescription() {
        return "Create a project";
    }

    @Override
    public void onCommand(Scanner scanner) {
        String name = getStringInput(scanner, "Choose a name for the project:");
        Project project = app.createProject(name);
        System.out.println("Created project " + name + " with ID " + project.getID());
    }
}
