Feature: Project report
	Description: A project leader views total time spent on a project
	Actors: Project leader

Scenario: View total time spent on project
	Given there is a project with name "Testing26001"
	And the employee "bap" is the project leader of the project
	And the project has an activity with name "Testing"
	And the employee "ber" has registered 20 hours on activity "Testing" under project "Testing26001" on date "2026-03-16"
	When the project leader requests a project report on project "Testing26001"
	Then the report shows "20" total hours spent on the project

Scenario: View report for non-existing activity
	Given there is a project with name "Testing26001"
	And the employee "bap" is the project leader of the project
	When the project leader requests time used on activity "Testing" on project "Testing26001"
	Then the error message "Activity does not exist" is given