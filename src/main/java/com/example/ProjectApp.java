package com.example;

import java.util.ArrayList;
import java.util.List;

public class ProjectApp {
    private List<Employee> employees = new ArrayList<>();

    protected Employee createUser(String name) {
        Employee user = new Employee(name);
        employees.add(user);
        return user;
    }

    protected Employee getUser(String name) {
        for (Employee e : employees) {
            if (e.getID().equals(name)) return e;
        }

        // employees.remo
        return null;
    }
}
