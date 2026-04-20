package com.example;

import java.util.List;

public class Report {
    String title;
    String hours;
    String employeeCount;
    String projectLeader;
    String activityCount;

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

    Report(Project project) {
        float totalHalfHours = project.getTimeUsed();
        this.title = project.getID();
        String hours = Double.toString(totalHalfHours / 2.);
        if (hours.endsWith(".0")) hours = hours.substring(0, hours.length()-2);
        this.hours = hours;
        this.employeeCount = "[Count]";
        this.projectLeader = project.getProjectLeader() == null ? "[NONE]" : project.getProjectLeader().getID();
        this.activityCount = "" + project.getActivities().size();
    }

    private int getLongestStringLength(String[] strings) {
        if (strings.length == 0) return 0;
        int longest = strings[0].length();
        for (String string: strings) {
            if (string.length() > longest) longest = string.length();
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
        int contentLength = getLongestStringLength( new String[] {
                titleString,
                hoursString,
                employeeCountString,
                projectLeaderString,
                activityCountString
        });
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
    }
}