package com.example;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Employee {
    private String ID;
    private List<Project> projects;
    private List<PersonalActivity> personalActivities;
    private List<Activity> assignedActivities = new ArrayList<>();

    public Employee(String ID) { // Written by BAP
        this.ID = ID;
        this.projects = new ArrayList<Project>();
        this.personalActivities = new ArrayList<PersonalActivity>();
    }

    public void addProject(Project project) {
        projects.add(project);
    } // Written by BAP

    public List<Project> getProjects() {
        return projects;
    } // Written by BAP

    public String getID() {
        return ID;
    } // Written by BAP

    public void addPersonalActivity(String name, String from, String to) { // Written by OFK
        LocalDate start = LocalDate.parse(from);
        LocalDate end = LocalDate.parse(to);
        if(start.isAfter(end)) throw new IllegalArgumentException("Start date is after end date");
        for (int i = 0; i < personalActivities.size(); i++) {
            PersonalActivity pa = personalActivities.get(i);

            if (pa.getName().equals(name) && pa.getStartDate().equals(start) && pa.getEndDate().equals(end)) {
                throw new IllegalArgumentException("Personal activity already exists");
            }
        }
        personalActivities.add(new PersonalActivity(name, start, end));
    }


    public boolean hasPersonalActivity(String name) { // Written by OFK
        for (int i = 0; i < personalActivities.size(); i++) {
            PersonalActivity pa = personalActivities.get(i);

            if (pa.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    public void assignToActivity(Activity activity) {
        assignedActivities.add(activity);
    } // Written by BAP

    
    public boolean isAvailable(int week, int year) {// Written by OFK
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        for (int i = 0; i < assignedActivities.size(); i++) {
            Activity a = assignedActivities.get(i);

            int actWeek = a.getStartDate().get(weekFields.weekOfWeekBasedYear());
            int actYear = a.getStartDate().getYear();
            if (actWeek == week && actYear == year) {
                return false;
            }
        }
        for (int i = 0; i < personalActivities.size(); i++) {
            PersonalActivity pa = personalActivities.get(i);

            int startYear = pa.getStartDate().getYear();
            int startWeek = pa.getStartDate().get(weekFields.weekOfWeekBasedYear());
            int endYear = pa.getEndDate().getYear();
            int endWeek = pa.getEndDate().get(weekFields.weekOfWeekBasedYear());

            boolean afterStart = year > startYear || (year == startYear && week >= startWeek);
            boolean beforeEnd = year < endYear || (year == endYear && week <= endWeek);

            if (afterStart && beforeEnd) {
                return false;
            }
        }
        return true;
    }
    public Project getProject(int id) { // Written by BAP
        for (Project p : projects) {
            if (p.getID() == id) return p;
        }

        return null;
    }
    public Activity getActivity(String activityID) { // Written by BAP
        for(Activity a : assignedActivities) {
            if(a.getID().equals(activityID)) {return a;}
        }
        return null;
    }
    public void addContribution(int projectID, String activityID, Contribution contribution) {
        getProject(projectID).getActivity(activityID).addContribution(contribution);
    }
    public boolean deleteActivity(String activityID) { // Written by BAP & assert written by MJ
        // Pre-conditions
        int orginalSize = assignedActivities.size();
        assert assignedActivities != null;
        assert activityID != null;
        for (Activity activity: assignedActivities) {
            assert activity != null;
            assert activity.getID() != null;
        }

        for(int i = 0; i < assignedActivities.size(); i++) {
           if(assignedActivities.get(i).getID().equals(activityID)) {
               assignedActivities.remove(i);

               assert assignedActivities.size() == orginalSize - 1; // Post-condition
               return true;
           }
        }
        assert assignedActivities.size() == orginalSize; // Post-condition
        return false;
    }
    public double viewHours(LocalDate date) { // Written by BAP
        int totalHalfHours = 0;
        for (Activity a : assignedActivities) {
            List<Contribution> contributions = a.getContributions(this.ID);
            if(contributions == null) {
                continue;
            }

            for(Contribution c : contributions) {
                if(c != null && c.getDate().equals(date)) {
                    totalHalfHours += c.getWorkTime();
                }
            }
        }
        return totalHalfHours / 2.0;
    }
}
