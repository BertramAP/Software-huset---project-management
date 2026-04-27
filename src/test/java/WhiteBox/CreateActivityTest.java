// This file is the whitebox test for the createActivity function, in the ProjectApp class
package WhiteBox;

import com.example.Activity;
import com.example.Project;
import com.example.ProjectApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CreateActivityTest {
    private ProjectApp app;
    private LocalDate startDate;
    private LocalDate endDate;

    @BeforeEach
    public void setUp() {
        this.app = new ProjectApp();
        this.startDate = LocalDate.parse("2026-04-23");
        this.endDate = LocalDate.parse("2026-12-31");
    }

    @Test
    public void testTC1CreateActivityForNonExistingProject() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                this.app.createActivity(1, "Act A", this.startDate, this.endDate, 5, "bap"));

        assertEquals("Project does not exist", exception.getMessage());
        assertEquals(0, this.app.getProjects().size());
    }

    @Test
    public void testTC2CreateDuplicateActivityForExistingProject() {
        Project project = this.app.createProject("Project A");
        Activity existingActivity = this.app.createActivity(project.getID(), "Act A", this.startDate, this.endDate, 5, "bap");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                this.app.createActivity(project.getID(), "Act A", this.startDate, this.endDate, 5, "bap"));

        assertEquals("Activity already exists!", exception.getMessage());
        assertEquals(1, project.getActivities().size());
        assertSame(existingActivity, project.getActivity("Act A"));
    }

    @Test
    public void testTC3CreateNewActivityForExistingProject() {
        Project project = this.app.createProject("Project A");

        Activity activity = this.app.createActivity(project.getID(), "Act A", this.startDate, this.endDate, 5, "bap");

        assertNotNull(activity);
        assertEquals("Act A", activity.getID());
        assertEquals(1, project.getActivities().size());
        assertSame(activity, project.getActivity("Act A"));
    }
}
