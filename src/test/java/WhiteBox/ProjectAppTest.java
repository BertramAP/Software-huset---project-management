package WhiteBox;

import com.example.Project;
import com.example.ProjectApp;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectAppTest {

    @Test
    void GivenManyNames_WhenCreatingProject_ShouldIncrementIds() {
        ProjectApp app = new ProjectApp();
        int year = (LocalDate.now().getYear() - 2000) * 1000;

        Project project1 = app.createProject("myProject");
        assertEquals(year + 1, project1.getID());

        Project project2 = app.createProject("test");
        assertEquals(year + 2, project2.getID());

        Project project3 = app.createProject("cli");
        assertEquals(year + 3, project3.getID());
    }

    @Test
    void givenBlankName_WhenCreatingProject_ThenShouldReturnProject() {
        ProjectApp app = new ProjectApp();
        Project project = app.createProject("");
        assertNotNull(project);
    }

    @Test
    void giveNonUniqueNames_WhenCreatingProject_ThenShouldGiveUniqueIds() {
        ProjectApp app = new ProjectApp();

        String projectName = "myProject";
        Project project1 = app.createProject(projectName);
        Project project2 = app.createProject(projectName);

        assertNotEquals(project1.getID(), project2.getID());
    }
}
