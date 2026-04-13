Feature: Check employee availability
	Description: A project leader checks which employees are available for a given time period
	Actors: Project leader

Scenario: Employee is available
	Given there is an employee with initials "bap"
    And there is an employee with initials "ber"
	And the employee "ber" has no activities in week 12 of 2026
	When the project leader checks availability for week 12 of 2026
	Then the employee "ber" is listed as available

Scenario: Employee is not available
	Given there is an employee with initials "ber"
	And the employee "ber" is assigned to an activity in week 12 of 2026
	When the project leader checks availability for week 12 of 2026
	Then the employee "ber" is not listed as available