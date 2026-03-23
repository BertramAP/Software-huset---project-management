Feature: Create project
	Description: An employee creates a new project
	Actors: Employee

Scenario: Create a project successfully
	Given there is an employee with initials "bap"
	When the employee creates a project with name "Testing26001"
	Then the project is created

Scenario: Create a project with a duplicate name
	Given there is an employee with initials "bap"
	And there is a project with name "Testing26001"
	When the employee creates a project with name "Testing26001"
	Then the error message "Project name already exists" is given