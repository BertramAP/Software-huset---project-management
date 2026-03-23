Feature: View registered hours
	Description: An employee views their registered hours for a day
	Actors: Employee

Scenario: View registered hours for today
	Given there is an employee with initials "bap"
        And there is a project with name "Test"
	And the employee "bap" has registered 4 hours on activity "Testing" on date "2026-03-16"
	And the employee "bap" has registered 3 hours on activity "Test" on date "2026-03-16"
	When the employee "bap" views registered hours for date "2026-03-16"
	Then the total registered hours are 7

Scenario: View registered hours when no hours are registered
	Given there is an employee with initials "bap"
	When the employee "bap" views registered hours for date "2026-03-16"
	Then the total registered hours are 0