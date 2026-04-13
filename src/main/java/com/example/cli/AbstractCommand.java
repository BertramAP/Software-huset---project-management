package com.example.cli;

import com.example.ProjectApp;

public abstract class AbstractCommand {
    protected ProjectApp app;

    public AbstractCommand(ProjectApp app) {
        this.app = app;
    }

    abstract public boolean onCommand(String[] args);
    abstract public String getUsage();
}
