// This file is the whitebox test for the removeEmployee function, in the Project class
package WhiteBox;

import com.example.Employee;
import com.example.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveEmployeeTest {
    private Project project;

    @BeforeEach
    public void setUp() {
        this.project = new Project(1, "Project A");
    }

    @Test
    public void testTC1RemoveExistingEmployeeFromNonEmptyList() {
        this.project.assignEmployee(new Employee("huba"));
        this.project.assignEmployee(new Employee("bap"));

        this.project.removeEmployee("huba");

        assertFalse(this.project.hasEmployee("huba"));
        assertTrue(this.project.hasEmployee("bap"));
    }

    @Test
    public void testTC2RemoveNonExistingEmployeeFromNonEmptyList() {
        this.project.assignEmployee(new Employee("bap"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                this.project.removeEmployee("huba"));

        assertEquals("Employee is not assigned to the project", exception.getMessage());
        assertTrue(this.project.hasEmployee("bap"));
    }

    @Test
    public void testTC3RemoveEmployeeFromEmptyList() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                this.project.removeEmployee("huba"));

        assertEquals("Employee is not assigned to the project", exception.getMessage());
    }
}
