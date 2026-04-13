package com.example;

public class Main {
    public static void main(String[] args) {
        Cli cli = new Cli();
        ProjectApp app = new ProjectApp();
        app.createUser("huba");
        cli.setApp(app);

        cli.start();
    }
}
