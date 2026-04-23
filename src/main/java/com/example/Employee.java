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

    public Employee(String ID) {
        this.ID = ID;
        this.projects = new ArrayList<Project>();
        this.personalActivities = new ArrayList<PersonalActivity>();
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public String getID() {
        return ID;
    }

    public void addPersonalActivity(String name, String from, String to) {
        LocalDate start = LocalDate.parse(from);
        LocalDate end = LocalDate.parse(to);
        personalActivities.add(new PersonalActivity(name, start, end));
    }


    public boolean hasPersonalActivity(String name) {
        return personalActivities.stream().anyMatch(a -> a.getName().equals(name));
    }


    public void assignToActivity(Activity activity) {
        assignedActivities.add(activity);
    }

    
    public boolean isAvailable(int week, int year) {
        for (Activity a : assignedActivities) {
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int actWeek = a.getStartDate().get(weekFields.weekOfWeekBasedYear());
            int actYear = a.getStartDate().getYear();
            if (actWeek == week && actYear == year) {
                return false;
            }
        }
        return true;
    }
    public Project getProject(String name) {
        for (Project p : projects) {
            if (p.getID().equals(name)) return p;
        }

        return null;
    }
    public Activity getActivity(String activityID) {
        for(Activity a : assignedActivities) {
            if(a.getID().equals(activityID)) {return a;}
        }
        return null;
    }
    public void addContribution(String projectID, String activityID, Contribution contribution) {
        getProject(projectID).getActivity(activityID).addContribution(contribution);
    }
    public boolean deleteActivity(String activityID) {
        for(int i = 0; i < assignedActivities.size(); i++) {
           if(assignedActivities.get(i).getID().equals(activityID)) {
               assignedActivities.remove(i);
               return true;
           }
        }
        return false;
    }
    public int viewHours(LocalDate date) {
        int totalRegisteredHours = 0;
        for (Activity a : assignedActivities) {
            List<Contribution> contributions = a.getContributions(this.ID);
            if(contributions == null) {
                continue;
            }

            for(Contribution c : contributions) {
                if(c != null && c.getDate().equals(date)) {
                    totalRegisteredHours += c.getWorkTime() / 2;
                }
            }
        }
        return totalRegisteredHours;
    }
}
