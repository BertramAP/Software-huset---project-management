package WhiteBox;

import com.example.Project;
import com.example.ProjectApp;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectAppTest {

    @Test
    void createProject() {
        ProjectApp app = new ProjectApp();
        int year = (LocalDate.now().getYear() - 2000) * 1000;

        Project project1 = app.createProject("myProject");
        assertEquals(year + 1, project1.getID());

        Project project2 = app.createProject("myProject");
        assertEquals(year + 2, project2.getID());

        Project project3 = app.createProject("myProject");
        assertEquals(year + 3, project3.getID());
    }
}
