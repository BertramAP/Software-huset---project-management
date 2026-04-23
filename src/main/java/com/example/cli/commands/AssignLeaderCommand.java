package com.example.cli.commands;

import com.example.Cli;
import com.example.Employee;
import com.example.Project;
import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

public class AssignLeaderCommand extends AbstractCommand {

    public AssignLeaderCommand(ProjectApp app, Cli cli) {
        super(app, cli);
    }

    @Override
    public String getUsage() {
        return "assign-leader <project-id> <username>";
    }

    @Override
    public String getDescription() {
        return "Assigns a user as the leader of a project";
    }

    @Override
    public boolean onCommand(String[] args) {
        if (!args[0].equals("assign-leader"))
            return false;
        if (args.length < 2)
            throw new IllegalArgumentException("Usage: " + getUsage());

        Employee employee = app.getEmployee(args[2]);
        if (employee == null)
            throw new IllegalArgumentException("User does not exist!");

        int projectId = Integer.parseInt(args[1]);
        Project project = app.getProject(projectId);
        if (project == null)
            throw new IllegalArgumentException("Project does not exist!");

        project.assignProjectLeader(employee);
        System.out.println("Assigned " + employee.getID() + " to " + args[2]);
        return true;
    }
}
