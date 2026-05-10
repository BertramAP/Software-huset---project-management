package com.example.cli.commands;

import com.example.Cli;
import com.example.Project;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

import java.util.Scanner;

public class ReportCommand extends AbstractCommand {

    public ReportCommand(String usage, ProjectApp app, Cli cli) {
        super(usage, app, cli);
    }

    @Override
    public String getDescription() {
        return "Print a report of every project";
    }

    @Override
    public void onCommand(Scanner scanner) {
        for (Project project : app.getProjects()) {
            project.generateReport().printReport();
        }
        System.out.println();
    }
}
