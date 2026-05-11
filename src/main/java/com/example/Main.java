package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cli cli = new Cli();
        ProjectApp app = new ProjectApp();
        app.createEmployee("huba");

        try {
            Scanner scanner = new Scanner(new File("users.txt"));
            while (scanner.hasNext()) {
                app.createEmployee(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            //System.out.println("users.txt not found, skipping user import");
        }

        cli.setApp(app);

        cli.start();
    }
}
