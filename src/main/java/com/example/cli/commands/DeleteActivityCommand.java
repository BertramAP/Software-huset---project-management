package com.example.cli.commands;

import com.example.*;
import com.example.cli.AbstractCommand;

import java.util.Scanner;
import java.util.stream.Collectors;
// Written by BAP
public class DeleteActivityCommand extends AbstractCommand {
    public DeleteActivityCommand(String usage, ProjectApp app, Cli cli) {
        super(usage, app, cli);
    }

    @Override
    public void onCommand(Scanner scanner) {
        int projectId = promptForProjectId(scanner);
        Project project = app.getProject(projectId);
        if (project == null)
            throw new IllegalArgumentException("Project does not exist!");

        System.out.println("Select an activity:");
        String activites = project.getActivities().stream().map(activity -> activity.getID() + " ").collect(Collectors.joining());
        System.out.println(activites);
        String activityName = getStringInput(scanner);
        Activity activity = app.getProject(projectId).getActivity(activityName);
        if(activity == null) throw new IllegalArgumentException("Activity does not exist");
        for(Employee e : activity.getEmployees()) {
            e.deleteActivity(activityName);
        }
        boolean complete = app.deleteActivity(activityName, projectId);
        if(!complete) throw new IllegalArgumentException("The activity was not deleted");
        System.out.println("Activity deleted " + activityName + " from project " + project.getName());

    }

    @Override
    public String getDescription() {
        return "Delete activity from project";
    }
}
