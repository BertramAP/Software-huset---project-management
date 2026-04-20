package com.example.cli.commands;

import com.example.Cli;
import com.example.Project;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

public class CreateProjectCommand extends AbstractCommand {

    public CreateProjectCommand(ProjectApp app, Cli cli) {
        super(app, cli);
    }

    @Override
    public String getUsage() {
        return "create-project <name>";
    }

    @Override
    public boolean onCommand(String[] args) {
        if (!args[0].equals("create-project"))
            return false;
        if (args.length < 2)
            throw new IllegalArgumentException("Usage: " + getUsage());

        Project project = app.createProject(args[1]);
        System.out.println("Created project " + args[1] + " with ID " + project.getID());
        return true;
    }
}
