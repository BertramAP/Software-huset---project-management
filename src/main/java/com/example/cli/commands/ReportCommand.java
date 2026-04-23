package com.example.cli.commands;

import com.example.Cli;
import com.example.Project;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

public class ReportCommand extends AbstractCommand {

    public ReportCommand(ProjectApp app, Cli cli) {
        super(app, cli);
    }

    @Override
    public String getUsage() {
        return "report";
    }

    @Override
    public String getDescription() {
        return "Print a report of every project";
    }

    @Override
    public boolean onCommand(String[] args) {
        if (!args[0].equals("report"))
            return false;

        for (Project project : app.getProjects()) {
            project.generateReport().printReport();
        }

        return true;
    }
}
