package com.example;

import java.util.Calendar;

public class Contribution {
    private Employee employee;
    private int workTime;
    private Calendar date;

    public Contribution(Employee employee, int workTime, Calendar date) {
        this.employee = employee;
        this.workTime = workTime;
        this.date = date;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void updateTime(int workTime) {
        this.workTime = workTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Calendar getDate() {
        return date;
    }
}
