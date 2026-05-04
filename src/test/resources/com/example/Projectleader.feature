Feature: Assign project leader
    Description: A project leader is assigned to a project
    Actors: Employee

Scenario: Assign a project leader successfully
    Given there is a project with name "Testing26001"
    And there is an employee with initials "bap"
    When the employee "bap" is assigned as project leader
    Then the employee "bap" is the project leader of the project

Scenario: Assign a project leader when one already exists
    Given there is a project with name "Testing26001"
    And there is an employee with initials "bap"
    And the employee "bap" is the project leader of the project
    And there is an employee with initials "ber"
    When the employee "ber" is assigned as project leader
    Then the error message "Project already has a project leader" is given