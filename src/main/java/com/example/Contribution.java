package com.example;

import java.time.LocalDate;
public class Contribution {
    private Employee employee;
    private int workTime;
    private LocalDate date;

    public Contribution(Employee employee, int workTime, LocalDate date) { // Written by AK
        if (workTime < 0) throw new IllegalArgumentException("Hours must be positive");
        this.employee = employee;
        this.workTime = workTime;
        this.date = date;
    }


    public int getWorkTime() { // Written by AK
        return workTime;
    }

    public void updateTime(int workTime) { // Written by AK
        this.workTime = workTime;
    }

    public Employee getEmployee() { // Written by AK
        return employee;
    }

    public LocalDate getDate() { // Written by AK
        return date;
    }

}
