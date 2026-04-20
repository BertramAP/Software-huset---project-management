package com.example.cli;

import com.example.Cli;
import com.example.ProjectApp;

public abstract class AbstractCommand {
    protected ProjectApp app;
    protected Cli cli;

    public AbstractCommand(ProjectApp app, Cli cli) {
        this.app = app;
        this.cli = cli;
    }

    abstract public boolean onCommand(String[] args);
    abstract public String getUsage();
}
