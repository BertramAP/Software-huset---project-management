package com.example;

import java.time.LocalDate;

public class Contribution {
    private Employee employee;
    private int workTime;
    private LocalDate date;

    public Contribution(Employee employee, int workTime, LocalDate date) {
        if (workTime < 0) throw new IllegalArgumentException("Hours must be positive");
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

    public LocalDate getDate() {
        return date;
    }

}
