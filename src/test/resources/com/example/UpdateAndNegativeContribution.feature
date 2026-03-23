Feature: Update register Time
	Description: An employee edits already registered time spent on an activity
	Actors: Employee
Scenario: Edit already registered time
    Given there is an employee with initials "bap"
	And there is a project with name "Testing26001"
	And the project has an activity with name "Testing"
	And the employee "bap" has registered 4 hours on activity "Testing" on date "2026-03-22"
	And the employee "bap" changes the registered hours to 6 on activity "Testing" on date "2026-03-22"
    When the employee "bap" views registered hours for date "2026-03-22"
	Then 6 hours are registered for employee "bap" on activity "Testing" on date "2026-03-22"

Scenario: Register negative hours
	Given there is a project with name "Testing26001"
	And the project has an activity with name "Testing"
	And there is an employee with initials "bap"
	When the employee "bap" registers -2 hours on activity "Testing" on date "2026-03-22"
	Then the error message "Hours must be positive" is given