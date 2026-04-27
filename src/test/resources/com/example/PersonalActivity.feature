Feature: Register personal activity
  Description: An employee registers a personal activity
  Actors: Employee

Scenario: Register a personal activity successfully
  Given there is an employee with initials "abc"
  When the employee "abc" registers a personal activity "holiday" from "2026-01-01" to "2026-01-02"
  Then the personal activity "holiday" is registered for employee "abc"

Scenario: Employee with personal activity is unavailable
  Given there is an employee with initials "abc"
  When the employee "abc" registers a personal activity "holiday" from "2026-03-16" to "2026-03-22"
  Then the employee "abc" is not listed as available in week 12 of 2026