package com.example;

import java.util.ArrayList;
import java.util.Scanner;

import com.example.cli.AbstractCommand;
import com.example.cli.commands.AssignActivityCommand;
import com.example.cli.commands.CreateActivityCommand;
import com.example.cli.commands.CreateProjectCommand;
import com.example.cli.commands.CreateUserCommand;
import com.example.cli.commands.RegisterTimeCommand;
import com.example.cli.commands.ReportCommand;

public class Cli {
    private ProjectApp app;
    private Employee currentUser;
    private ArrayList<AbstractCommand> commands = new ArrayList<>();

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

    public void onCommand(String[] args) {
        if (args.length == 0) return;

        if (args[0].equals("help")) {
            printHelp();
        }

        for (AbstractCommand c : commands) {
            if (c.onCommand(args)) return;
        }

        System.out.println("Unknown command");
    }

    private String repeatChar(String c, int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(c);
        }
        return builder.toString();
    }

    private void printHelp() {
        int longestUsage = 0;
        for (AbstractCommand c : commands) {
            String usage = c.getUsage();
            if (usage.length() > longestUsage) longestUsage = usage.length();
        }

        System.out.println("Commands:");
        for (AbstractCommand c : commands) {
            String usage = c.getUsage();
            System.out.println(usage + repeatChar(" ", longestUsage - usage.length()) + "\t" + c.getDescription());
        }
        System.out.println("exit");
        return;
    }

    public void setApp(ProjectApp app) {
        this.app = app;
        commands.clear();
        commands.add(new AssignActivityCommand(app, this));
        commands.add(new CreateActivityCommand(app, this));
        commands.add(new CreateProjectCommand(app, this));
        commands.add(new CreateUserCommand(app, this));
        commands.add(new RegisterTimeCommand(app, this));
        commands.add(new ReportCommand(app, this));
    }

    public Employee getCurrentUser() {
        return currentUser;
    }
}
