package com.example.cli.commands;

import com.example.ProjectApp;
import com.example.cli.AbstractCommand;

public class AssignCommand extends AbstractCommand {

    public AssignCommand(ProjectApp app) {
        super(app);
    }

    @Override
    public String getUsage() {
        return "assign <projectId> <userId>";
    }

    @Override
    public boolean onCommand(String[] args) {
        if (!args[0].equals("assign")) return false;
        if (args.length < 3)
            throw new IllegalArgumentException("Usage: " + getUsage());

        app.assignEmployee(args[1], args[2]);
        return true;
    }
}
