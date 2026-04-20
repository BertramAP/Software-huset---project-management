Feature: Remove employee from project
  Description: A project leader removes an employee from a project
  Actors: Project leader

Scenario: Remove an employee from a project successfully
  Given there is a project with name "Testing26001"
  And there is an employee with initials "bap"
  And the employee "bap" is assigned to the project
  When the employee "bap" is removed from the project
  Then the employee "bap" is no longer assigned to the project

Scenario: Remove an employee who is not assigned to the project
  Given there is a project with name "Testing26001"
  And there is an employee with initials "bap"
  When the employee "bap" is removed from the project
  Then the error message "Employee is not assigned to the project" is given
