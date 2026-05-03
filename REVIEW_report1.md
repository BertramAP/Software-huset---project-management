# Review of Report 1 + Codebase — 02161 Software Engineering 1

This document consolidates the peer-review feedback, the project requirements (`project.pdf`), and the current state of Report 1 + the Maven project in this folder. It identifies concrete gaps and inconsistencies to resolve before Report 2 / the final hand-in.

---

## 1. Verdict at a glance

The skeleton is solid: the core minimum-requirements flow (create project → create activity → assign employee → register time → report) is implemented and has Cucumber coverage. But the report and the code are out of sync on almost every name, and a handful of functional requirements and diagram-notation issues remain. The most important things to fix are:

1. **Actor inheritance** in use-case & sequence diagrams (feedback 1, 2, 3).
2. **Employee vs. User** duplication in the code and diagrams (feedback 18).
3. **Personal vs. fixed activity** naming clash (feedback 6, 17).
4. **Project should have a name separate from the auto-generated project number** (feedback 16 + §5.1 of the project description).
5. **Too few detailed use cases** (feedback 10: 8 instead of ≥10 for a 5-person group).
6. **Missing use case for "registration of leave / sickdays / courses"** (feedback 11) — the code has `PersonalActivity`, but it is not on the use-case diagram.
7. **Class-diagram methods/attributes do not match the Java code.**
8. **Cucumber feature names don't match the use-case names** (feedback 15).
9. **Class diagram is missing arrowheads, role labels, and is hard to read** (feedback 9, 12, 13).
10. **Report generation does not compare time spent with budgeted time** — required by §5.2.

---

## 2. Requirements coverage (project.pdf §5 + §5.2)

| Requirement | Status | Notes |
|---|---|---|
| Create project | OK | `ProjectApp.createProject(name)` |
| Create and add activity to project | OK | `Project.addActivity(Activity)` |
| Assign employee to activity | OK | `Activity.addEmployee(Employee)` |
| Assign project leader to project | OK | `Project.assignProjectLeader(Employee)` |
| Register time on activity | OK | `Activity.addContribution(...)`, half-hour precision via `hours*2` |
| **Report comparing spent vs. budgeted time** | **GAP** | `Project.generateReport()` only prints total time spent. The brief explicitly requires "hvor meget tid der allerede er brugt på projektet sammenlignet med den budgetterede tid" + "forventede restarbejde". Neither is shown. |
| Employee "huba" present at startup | OK | `Main.java` line 7 |
| Project numbers auto-assigned in `YYNNN` format | **GAP** | `Project.projectID` is whatever string the caller passes (e.g., `"Testing26001"`). No auto-numbering. |
| Project has a name separate from the number | **GAP** | No `name` field. Feedback 16 hits this. |
| Employee initials (≤4 letters) | PARTIAL | No length check, but free-form strings are accepted. Either validate or note the simplification in the discussion. |
| Week-number resolution for activities | OK | `Activity` stores `LocalDate`; `Employee.isAvailable(week, year)` uses `WeekFields`. |
| Personal / fixed activities (leave, sickness, courses) with start- and end-date | PARTIAL | `PersonalActivity` exists; **but** it has no corresponding use case on the use-case diagram and the terminology mixes "personal" and "fixed". |
| Overview of available employees | OK | `Employee.isAvailable` + `EmployeeAvailability.feature` |
| Edit already-registered time | OK | `Contribution.updateTime` + `UpdateAndNegativeContribution.feature` |
| Import HR list from file | OMITTED (valid afgrænsning) | Fine to leave out, but the discussion should explicitly justify the scope cut. |
| Remaining work on a project (for weekly project meetings) | **GAP** | Not implemented. |

Because §7 (evaluation) says "det er ikke afgørende hvor meget af funktionaliteten … der er implementeret, men snarere kvaliteten", the gaps above do not have to be filled in code — but they must be either **implemented** or **explicitly acknowledged as afgrænsninger in the discussion**.

---

## 3. Terminology drift (biggest single source of reviewer pain)

### 3.1 "Employee" vs. "User"
The glossary and Cucumber features use only **Employee**. But the code and some diagrams alternate:

- `ProjectApp.createUser()`, `getUser()` → should be `createEmployee` / `getEmployee`.
- CLI: `CreateUserCommand`, `create-user <name>`, `"Sign in: "`, `"User not found"`, `currentUser`, `username`.
- Sequence diagrams: `createUser("ber")` with return type `anEmployee` — the diagram itself names the mismatch.
- `AssignCommand` usage: `assign <projectId> <userId>`.
- Diagram field `Employee.userID` vs. Java field `Employee.ID`.

**Fix:** pick **Employee** everywhere (matches glossary). Rename the class-diagram field, the `ProjectApp` methods, the CLI command, the sequence-diagram messages, and the user-facing strings. (Feedback 18 calls this out explicitly.)

### 3.2 "Personal activity" vs. "fixed activity"
- The glossary entry literally reads: *"**Personal Activity:** A fixed activity is a non-project activity, such as vacation..."* — the definition contradicts its own header.
- §5 of the project brief uses **"faste aktiviteter"**.
- The Java class and feature file use **PersonalActivity**.
- Feedback 6 and 17 both hit this.

**Fix:** pick one term and use it consistently. Recommendation: use **Fixed Activity** (matches the brief) in the glossary, use-case diagram, sequence diagrams, and class diagram — and rename the Java class & feature file to match. If you keep `PersonalActivity`, update the glossary definition to match.

### 3.3 Use-case name ↔ Cucumber feature ↔ code operation
Feedback 15 ("pedantic, but the names … should match") applies to multiple pairs. Current state:

| Use-case diagram label | Cucumber Feature file & name | Code operation |
|---|---|---|
| Create Project | `CreateProject.feature` "Create project" | `ProjectApp.createProject` — OK |
| Create Activity for Project | `Activity.feature` "Create activity" | `Project.addActivity` — **mismatch** |
| Assign Employee to Activity | `Employee.feature` "Add employee to activity" | `Activity.addEmployee` — **3-way mismatch** (assign vs. add) |
| Register Time | *(no dedicated feature; scenario lives in `UpdateAndNegativeContribution.feature`)* | `Activity.addContribution` — **mismatch** |
| Edit Registered Time | `UpdateAndNegativeContribution.feature` "Update register Time" | `Contribution.updateTime` — **mismatch** |
| Assign Project Leader to Project | `Projectleader.feature` "Assign project leader" | `Project.assignProjectLeader` — OK |
| Set Activity Budget and Schedule | *(no Cucumber feature)* | *(no method — only set via Activity constructor)* — **gap** |
| Generate Project Time Report | `ProjectReport.feature` "Project report" | `Project.generateReport` — **mismatch** |
| View Available Employees | `EmployeeAvailability.feature` "Check employee availability" | `Employee.isAvailable` — **mismatch** |
| *(not on UC diagram)* | `ViewHours.feature` "View registered hours" | — | — should be added to UC diagram |
| *(not on UC diagram)* | `PersonalActivity.feature` "Register personal activity" | `Employee.addPersonalActivity` — should be added to UC diagram |

**Fix:** choose one set of names per operation and apply it to (a) use-case diagram label, (b) `Feature:` name in the .feature file, (c) method name in the class diagram, (d) method name in Java. Don't leave any operation where the three names differ.

### 3.4 Class diagram ↔ Java code
The diagram is out of date. Concrete mismatches:

| Diagram says | Code has |
|---|---|
| `ProjectApp.userList`, `projectList` | `employees`, `projects` |
| `ProjectApp.assignToActivity(projectID, activityID, employeeID)` | Does not exist; `assignEmployee(projectId, employeeId)` on project, not activity |
| `Project.assignEmployee(projectID, employeeID)` | `assignEmploye(Employee)` — **note the typo `assignEmploye`** (should be `assignEmployee`) |
| `Activity.budgetNoHours` | `budgetHalfHours` |
| `Activity.createID` | `creatorID` |
| `Activity.getContributionList(ID)`, `getCreator`, `getEmployeeList`, `getActivityList` | `getContribution(id)`, `hasEmployee(id)`, `addEmployee`, `getTimeUsed`, `getID` — different set |
| `Employee.userID`, `activityList` | `ID`, `assignedActivities` + `personalActivities` + `projects` |
| `Employee.addActivity(activityID, Contribution)`, `setPersonalActivities(PersonalActivity)`, `getInfo(ID)`, `deleteActivity`, `isAvailable(Calendar)` | `addProject`, `addPersonalActivity(name, from, to)`, `hasPersonalActivity`, `assignToActivity`, `isAvailable(int week, int year)` |
| `PersonalActivity.setStartDate(Calendar)` | `setStartDate(LocalDate)` — `java.util.Calendar` isn't used anywhere |
| `Contribution` (no operations shown) | `getWorkTime`, `updateTime`, `getEmployee`, `getDate` |

**Fix:** redraw the class diagram from the current code (but don't auto-generate it — §2(a) of the brief forbids that; draw it by hand in UMLet/Visual Paradigm to match the code). Include associations with **labels** ("worksOn", "leads", "contains", "worksOnActivity") and **arrowheads** (feedback 12, 13).

### 3.5 Typos and stragglers
- `Project.assignEmploye` → `assignEmployee` (missing `e`).
- `Projectleader.feature`: `"Assign a project leader succesfully"` → `successfully`.
- `Projectleader.feature` still tags Actors as "Employee" even though the use case is assigning a project leader — keep it as Employee (since any employee can do it) but be consistent with how the use-case diagram attributes the actor.
- `ProjectTest.java` line 34 calls `assignEmploye` — rename in lockstep with the fix above.
- Dead/leftover code: `App.java` is a duplicate of `ProjectApp` (used only by `AppHolder` in tests). Pick one and delete the other; currently production code uses `ProjectApp` but all step definitions go through `App` via `AppHolder.getApp()`. This divergence will bite you during Report-2 code-coverage and white-box testing.
- `ProjectReportTest.java` is 95% commented-out dead code — remove it.
- `Cli.java`'s `login` / `currentUser` naming reinforces the Employee/User split — rename once you unify 3.1.

---

## 4. Diagram-notation fixes (maps directly to the feedback file)

### 4.1 Use-case diagram (feedback 1)
Remove the `▷` generalisation arrow from **Project Leader** to **Employee**. A user can be *both* an employee and a project leader, so they are two **roles**, not two classes where one inherits from the other. Acceptable UML replacements:

- Draw them as two separate actors with no connecting line; or
- Show them both attached to the same use cases without inheritance; or
- Use `«extends»` on the use-case level if a Project-Leader-only use case extends an Employee use case — but **not** actor generalisation.

Apply the same fix on every sequence-diagram that shows an actor (feedback 2 mentions page 15).

### 4.2 Use-case diagram content (feedback 10, 11, 15)
- Add at least **two more detailed use cases** to reach the 10 required for a 5-person group. Natural candidates already implicit in the code:
  - **Register fixed activity** (leave, sickness, courses) — closes feedback 11.
  - **View registered hours for a day** — `ViewHours.feature` already exists.
- Align the use-case *names* with the Cucumber feature *names* (see table in §3.3). Rename both sides so they match.
- Add `«includes»`/`«extends»` only where they genuinely apply; don't invent relations to fill space.

### 4.3 Class diagram (feedback 7, 9, 12, 13)
- Redraw at higher resolution; current PDF render is unreadable (feedback 8 on page 9, feedback 13 on page 7).
- Put **arrowheads on every association** (aggregation diamonds where appropriate — `Project ◆— Activity`, `Project ◆— Employee` is wrong because employees are shared across projects, so use a plain association).
- Put **role labels on every association**: `Employee —worksOn→ Project`, `Employee —leads→ Project`, `Activity —contributors→ Contribution`, etc.
- Decide the **Activity / PersonalActivity** relationship (feedback 9):
  - Option A (recommended, matches current code): no inheritance; keep `PersonalActivity` as a separate class that an Employee owns, because personal activities have no `Contribution`, no project, no budget, no assigned list. Justify this in the Discussion — you already hint at it on p. 14 ("we have decided to split activities into two different classes").
  - Option B: make `PersonalActivity` extend `Activity` and refactor the code. Riskier mid-project, but arguably cleaner.
  - Pick one, document the decision explicitly.
- Make the class diagram match the Java that will be shipped in Report 2 (see §3.4).

### 4.4 Sequence diagrams
- Remove the Project-Leader ▷ Employee generalisation (feedback 2).
- Make method arrows use exactly the method names in the class diagram / code. Several diagrams use `createUser`, `assignToActivity(projectID, activity, initials)`, `assingProjectLeader` (typo in the diagram on page 7), `senProjectLeader` (typo on page 12, should be `setProjectLeader` or `assignProjectLeader`). Also `nemployee` on page 2/3 of the PDF looks like an OCR typo — verify it says "employee".
- Align returned object names (`anEmployee`, `aProject`, …) with class names after the Employee/User unification.

---

## 5. Discussion improvements (feedback 4, 5, 7, 14)

The current discussions are very short. Expand them along these lines:

### After the requirement specification (p. 5)
The current paragraph only raises the use-case-to-scenario question. Address instead:
- **Ambiguities in the glossary**: e.g. "fixed" vs. "personal" activity, project *name* vs. project *number*, what exactly an "Activity" includes.
- **Scope cuts (afgrænsninger)** you've made: no file persistence, no HR-file import, no auto-project-numbering (if you keep it that way), no access control, half-hour precision, ≤10 activities per employee not enforced, etc. — tie each decision back to a sentence in §5 that you chose not to implement.
- **Answer feedback 14 directly**: "one use case translates into multiple scenarios" — the main success scenario + alternative/error scenarios, which is why several of your feature files already contain two scenarios per feature.

### After the program design (p. 14)
Expand beyond the Activity/PersonalActivity-split rationale:
- Why `ProjectApp` sits between the UI and the domain classes (dependency inversion, supports future in-memory database swap, gets the UI out of the test loop).
- Why `Contribution` is its own class rather than a map `<date, hours>` on `Activity` (traceability to employee and date, supports editing).
- Why you use `LocalDate` rather than `Calendar`.
- Half-hour storage as `int` half-hours instead of `double` hours (avoid FP rounding, match the "1/2 time præcision" requirement).

### Every section that introduces a design choice
Feedback 7 asks for more reflection at the points where choices are made. Useful places:
- Glossary: justify why `Project Leader` is a role and not a separate class/actor (resolves feedback 1 conceptually).
- Class diagram: justify the 1:many associations and why `Project` owns `Activity` (composition) while `Employee` has an association to `Activity` (not composition).

---

## 6. Point-by-point response to `Feedback.txt`

| # | Feedback | Status | Concrete action |
|---|---|---|---|
| 1 | Inheritance arrow Project Leader → Employee on use-case diagram | Open | Remove the generalisation triangle; make them peer actors. |
| 2 | Same arrow on sequence diagram p. 15 | Open | Remove. |
| 3 | Same issue on p. 6 | Open | Remove. |
| 4 | Discussion needs ambiguities in glossary | Open | Add a paragraph as described in §5 above. |
| 5 | Last part of discussion too specific (p. 15) | Open | Replace with general reflections on overall structure (dependency inversion, layering, in-memory store). |
| 6 | Personal vs. fixed activity — two words for one thing | Open | Pick one term and unify. See §3.2. |
| 7 | More reflection at design-choice points | Open | See §5. |
| 8 | Diagram p. 9 hard to read | Open | Re-export at higher DPI, enlarge in LaTeX via `\includegraphics[width=…]`, or split into two diagrams. |
| 9 | Class-diagram relations/role labels + Activity-vs-PersonalActivity abstraction | Open | §4.3. Add labels. Decide + document the relationship. |
| 10 | Only 8 detailed use cases; need ≥10 for a 5-person group | Open | Add "Register fixed activity" and "View registered hours" as full detailed use cases. Both already have (partial) Cucumber files. |
| 11 | Missing use case for leave / sickdays / courses | Open | Same as #10 — add "Register fixed activity" use case on the diagram and as a detailed use case with at least main + alternative scenarios. |
| 12 | Missing association labels | Open | Add "worksOn", "leads", "containsActivity", "contributesTo", etc. |
| 13 | Missing arrowheads on class diagram | Open | Add arrowheads (navigability) and aggregation diamonds where applicable. |
| 14 | "One use case ≠ one scenario" + more discussion of glossary ambiguities | Open | Correct the claim in the current discussion. One use case = one main scenario **plus** alternative / error scenarios in the same `Feature:` file. |
| 15 | Use-case names should match Cucumber feature names | Open | See §3.3; rename both sides. |
| 16 | A project should be able to have a name | Open | Add `name` field to `Project`, to the class diagram, and to `createProject`. Then auto-generate the `projectID` in `YYNNN` form inside `ProjectApp`. Update the `Create project` Cucumber feature to pass both. |
| 17 | Duplicate of #6 | Open | Same fix. |
| 18 | Employee vs. User | Open | See §3.1; rename everything User → Employee in code and diagrams. |

---

## 7. Suggested order of work

1. Rename in code: `User` → `Employee` everywhere (ProjectApp, CLI command, Cli login); fix `assignEmploye` typo. Run tests.
2. Add `name` field to `Project`; have `ProjectApp.createProject` auto-generate `YYNNN` IDs. Update Cucumber `CreateProject.feature` to use project name + project ID.
3. Decide: is `PersonalActivity` → `FixedActivity`? Rename class, feature file, and glossary entry together.
4. Add two more detailed use cases ("Register fixed activity", "View registered hours") to the use-case diagram and to the report; their Cucumber files already exist.
5. Align all use-case / feature / method names (§3.3).
6. Redraw the class diagram from the final code — with role labels and arrowheads.
7. Update sequence diagrams to use the new names and to drop the actor generalisation.
8. Rewrite the two Discussion paragraphs per §5.
9. Delete the dead `App.java` and the commented-out `ProjectReportTest.java`; wire `AppHolder` to `ProjectApp` so production code and tests share one application class (this also matters for Report 2 code coverage).
10. Extend `Project.generateReport()` to include budgeted time and remaining work, or explicitly scope it out in the discussion.

Tackling 1–3 first gives you the terminology consistency the peer review was most insistent on; the rest are mostly diagram + prose work in the report.
