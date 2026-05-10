package com.example.cli.commands;

import com.example.Cli;
import com.example.Employee;
import com.example.Project;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

import java.util.Scanner;

// Written by DIS
public class AssignLeaderCommand extends AbstractCommand {

    public AssignLeaderCommand(String usage, ProjectApp app, Cli cli) {
        super(usage, app, cli);
    }

    @Override
    public String getDescription() {
        return "Assigns an employee as the leader of a project";
    }

    @Override
    public void onCommand(Scanner scanner) {
        Employee employee = app.getEmployee(getStringInput(scanner, "Type the employee's initals:"));
        if (employee == null)
            throw new IllegalArgumentException("Employee does not exist!");

        int projectId = promptForProjectId(scanner);
        Project project = app.getProject(projectId);
        if (project == null)
            throw new IllegalArgumentException("Project does not exist!");

        project.assignProjectLeader(employee);
        System.out.println("Assigned " + employee.getID() + " to " + project.getName());
    }
}
