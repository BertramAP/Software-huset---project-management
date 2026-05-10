package com.example.cli.commands;

import com.example.Cli;
import com.example.Employee;
import com.example.Project;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

import java.util.Scanner;
// Written by BAP
public class AssignProjectCommand extends AbstractCommand {

    public AssignProjectCommand(String usage, ProjectApp app, Cli cli) {
        super(usage, app, cli);
    }

    @Override
    public void onCommand(Scanner scanner) {
        int projectId = promptForProjectId(scanner);
        Project project = app.getProject(projectId);
        if (project == null)
            throw new IllegalArgumentException("Project does not exist!");

        Employee employee = app.getEmployee(getStringInput(scanner, "Type the employee's initals:"));
        if (employee == null)
            throw new IllegalArgumentException("Employee does not exist!");

        project.assignEmployee(employee);
        System.out.println("Assigned " + employee.getID() + " as an employee to " + project.getName() + "\n");
    }

    @Override
    public String getDescription() {
        return "Assign an Employee to a project";
    }


}