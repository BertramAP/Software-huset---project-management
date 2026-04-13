package com.example;

import java.util.Scanner;

public class Cli {
    private ProjectApp app;
    private Employee currentUser;

    Cli() {
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        login(scanner);

        while (true) {
            System.out.print("> ");
            String cmd = scanner.nextLine();
            if (cmd.equals("exit"))
                break;

            try {
                this.onCommand(cmd.split(" "));
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
        scanner.close();
    }

    private void login(Scanner scanner) {
        while (true) {
            System.out.print("Sign in: ");
            String username = scanner.nextLine();
            var user = app.getUser(username);
            if (user != null) {
                currentUser = user;
                System.out.println("Signed in successfully");
                break;
            }

            System.out.println("User not found");
        }
    }

    public void onCommand(String[] args) {
        if (args.length == 0) return;

        switch (args[0]) {
            case "create-project": {
                if (args.length < 2) throw new IllegalArgumentException("Please specify a name!");
        
                Project project = app.createProject(args[1]);
                System.out.println("Created project " + args[1] + " with ID " + project.getID());
                return;
            }

            case "assign": {
                if (args.length < 3) throw new IllegalArgumentException("Usage: assign <projectId> <userId>");

                app.assignEmployee(args[1], args[2]);
            }
        }

        System.out.println("Unknown command");
    }

    public void setApp(ProjectApp app) {
        this.app = app;
    }
}
