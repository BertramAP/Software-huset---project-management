Feature: Create activity
	Description: A project leader creates an activity in a project. If no project leader has been assigned, an employee can create the activity.
	Actors: Project leader, Employee

Scenario: Create an activity when no project leader is assigned
	Given there is a project with name "Testing26001"
	And the project has no project leader
	And the employee "bap" is assigned to the project
	When the employee "bap" creates an activity with name "Testing"
	Then the activity "Testing" is added to the project

Scenario: Create an activity with a duplicate name
	Given there is a project with name "Testing26001"
	And the employee "bap" is the project leader of the project
	And the project has an activity with name "Testing"
	When the project leader creates an activity with name "Testing"
	Then the error message "Activity already exists" is given