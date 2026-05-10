package com.example;

import java.util.HashMap;
import java.util.Scanner;

import com.example.cli.AbstractCommand;
import com.example.cli.commands.*;

public class Cli {
    private ProjectApp app;
    private Employee currentUser;
    private final HashMap<String, AbstractCommand> commands = new HashMap<>();

    Cli() {
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        login(scanner);
        System.out.println("Type \"help\" to view a list of available commands");

        while (true) {
            System.out.print("> ");
            String cmd = scanner.nextLine().split(" ")[0];
            if (cmd.equals("exit"))
                break;

            if (cmd.equals("help")) {
                printHelp();
                continue;
            }

            AbstractCommand command = commands.get(cmd);
            if (command == null) {
                System.out.println("Unknown command");
                continue;
            }

            try {
                command.onCommand(scanner);
            } catch (Exception error) {
                System.out.println(error.getMessage());
            }
        }
        scanner.close();
    }

    private void login(Scanner scanner) {
        while (true) {
            System.out.print("Sign in: ");
            String username = scanner.nextLine();
            var user = app.getEmployee(username);
            if (user != null) {
                currentUser = user;
                System.out.println("Signed in successfully");
                break;
            }

            System.out.println("User not found");
        }
    }

    private String repeatChar(String c, int n) {
        return String.valueOf(c).repeat(Math.max(0, n));
    }

    private void printHelp() {
        int longestUsage = 0;
        for (AbstractCommand c : commands.values()) {
            String usage = c.getUsage();
            if (usage.length() > longestUsage)
                longestUsage = usage.length();
        }

        System.out.println("Commands:");
        for (AbstractCommand c : commands.values()) {
            String usage = c.getUsage();
            System.out.println(usage + repeatChar(" ", longestUsage - usage.length()) + "\t" + c.getDescription());
        }
        System.out.println("exit");
    }

    public void setApp(ProjectApp app) {
        this.app = app;
        commands.clear();
        commands.put("assign-activity", new AssignActivityCommand("assign-activity", app, this));
        commands.put("assign-leader", new AssignLeaderCommand("assign-leader", app, this));
        commands.put("create-activity", new CreateActivityCommand("create-activity", app, this));
        commands.put("create-project", new CreateProjectCommand("create-project", app, this));
        commands.put("create-user", new CreateEmployeeCommand("create-user", app, this));
        commands.put("register-time", new RegisterTimeCommand("register-time", app, this));
        commands.put("report", new ReportCommand("report", app, this));
        commands.put("view-hours-on-date", new ViewHoursCommand("view-hours-on-date", app, this));
        commands.put("assign-project", new AssignProjectCommand("assign-project", app, this));
    }

    public Employee getCurrentUser() {
        return currentUser;
    }
}
