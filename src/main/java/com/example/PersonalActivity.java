package com.example;

import java.time.LocalDate;

public class PersonalActivity {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    public PersonalActivity(String name, LocalDate startDate, LocalDate endDate) { // Written by OFK
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() { // Written by OFK
        return name;
    }

    public LocalDate getStartDate() { // Written by OFK
        return startDate;
    }

    public LocalDate getEndDate() { // Written by OFK
        return endDate;
    }

    public void setStartDate(LocalDate startDate) { // Written by OFK
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) { // Written by OFK
        this.endDate = endDate;
    }
}
