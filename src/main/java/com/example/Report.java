package com.example;

import java.util.ArrayList;

public class Report {
    String title;
    String hours;
    String employeeCount;
    String projectLeader;
    String activityCount;
    String[] employees;

    public String getTitle() {
        return title;
    }

    public String getHours() {
        return hours;
    }

    public String getEmployeeCount() {
        return employeeCount;
    }

    public String getProjectLeader() {
        return this.projectLeader;
    }

    public String getActivityCount() {
        return activityCount;
    }

    private String[] mergeArrays(String[] first, String[] second) {
        String[] result = new String[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    Report(Project project) {
        float totalHalfHours = project.getTimeUsed();
        this.title = project.getName();
        String hours = Double.toString(totalHalfHours / 2.);
        if (hours.endsWith(".0")) hours = hours.substring(0, hours.length()-2);
        this.hours = hours;
        this.employeeCount = "" + project.employees.size();
        this.projectLeader = project.getProjectLeader() == null ? "[NONE]" : project.getProjectLeader().getID();
        this.activityCount = "" + project.getActivities().size();

        if (project.employees.isEmpty()) {
            employees = new String[] { "No registered employees" };
        }
        // Gather all employees into a String[]
        ArrayList<String> employees = new ArrayList<>();
        for (Employee employee : project.employees) {
            employees.add(employee.getID());
        }
        if (project.employees.isEmpty()) {
            this.employees = new String[] { "No registered employees" };
        }
        else {
            this.employees = new String[employees.size()];
            for (int i = 0; i < employees.size(); i++) {
                this.employees[i] = employees.get(i); // Can't figure out how to use .toArray
            }
        }
    }

    public static int getLongestStringLength(String[] strings) {
        assert strings != null; // pre-condition

        for (String string: strings) {
            assert string != null; // also post condition
        }

        if (strings.length == 0) return 0;
        int longest = strings[0].length();
        for (String string: strings) {
            if (string.length() > longest) longest = string.length();
        }
        assert longest >= 0; // Post condition
        for (String string: strings) {
            assert string.length() <= longest; // Post condition
        }
        return longest;
    }

    private void printHorizontalLine(int length) {
        String line = "+" + "-".repeat(length) + "+";
        System.out.println(line);
    }

    private void printCenteredText(String text, int contentLength) {
        int spacing = contentLength - text.length(); // One character margin on each side
        int leftSpacing = spacing / 2;
        int rightSpacing = spacing - leftSpacing;
        String centered = "|" + " ".repeat(leftSpacing) + text + " ".repeat(rightSpacing) + "|";
        System.out.println(centered);
    }

    public void printReport() {
        String titleString = "Project title: " + getTitle(); // Compiler bitches if you use this.title directly
        String hoursString = "Total hours registrered: " + getHours();
        String employeeCountString = "Employee Count: " + getEmployeeCount();
        String projectLeaderString = "Project Leader: " + getProjectLeader();
        String activityCountString = "Activity Count: " + getActivityCount();
        String employeeTitle = "Employees";
        int contentLength = getLongestStringLength( mergeArrays(new String[] {
                titleString,
                hoursString,
                employeeCountString,
                projectLeaderString,
                activityCountString,
                employeeTitle
                }, employees));
        contentLength += 2; // One character margin to project box sidelines

        // Printing
        printHorizontalLine(contentLength);
        printCenteredText(titleString, contentLength);
        printCenteredText(projectLeaderString, contentLength);
        printHorizontalLine(contentLength);
        printCenteredText(employeeCountString, contentLength);
        printCenteredText(hoursString, contentLength);
        printCenteredText(activityCountString, contentLength);
        printHorizontalLine(contentLength);
        printCenteredText(employeeTitle, contentLength);
        for (String employee: employees) {
            printCenteredText(employee, contentLength);
        }
        printHorizontalLine(contentLength);
    }
}