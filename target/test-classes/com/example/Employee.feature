Feature: Add employee to activity
	Description: An employee adds an employee to an activity
	Actors: Employee
    
Scenario: Add an employee to an activity successfully
	Given there is a project with name "Testing26001"
	And the project has an activity with name "Testing"
	And there is an employee with initials "ber"
	When the employee "ber" is added to activity "Testing"
	Then employee is added to activity "Testing"
    
Scenario: Add an employee who is already assigned
	Given there is a project with name "Testing26001"
	And the project has an activity with name "Testing"
	And there is an employee with initials "ber"
	And the employee "ber" is assigned to the activity "Testing"
	When the employee "ber" is added to activity "Testing"
	Then the error message "Employee is already assigned to the activity" is given