// This file is the whitebox test for the deleteActivity function, in the Employee class
package WhiteBox;

import com.example.Activity;
import com.example.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteActivityTest {
    private Employee emp;

    @BeforeEach
    public void setUp() {
        this.emp = new Employee("bap");
    }
    @Test
    public void testTC1DeleteExistingActivityFromNonEmptyList() {
        LocalDate startDate = LocalDate.parse("2026-04-23");
        LocalDate endDate = LocalDate.parse("2026-12-31");
        this.emp.assignToActivity(new Activity("Act A", startDate, endDate, 5, this.emp.getID()));
        this.emp.assignToActivity(new Activity("Act B", startDate, endDate, 5, this.emp.getID()));

        boolean result = this.emp.deleteActivity("Act A");
        assertTrue(result);
        assertNull(this.emp.getActivity("Act A"));
    }
    @Test
    public void testTC2DeleteNonExistingActivityFromNonEmptyList() {
        LocalDate startDate = LocalDate.parse("2026-04-23");
        LocalDate endDate = LocalDate.parse("2026-12-31");
        this.emp.assignToActivity(new Activity("Act A", startDate, endDate, 5, this.emp.getID()));

        boolean result = this.emp.deleteActivity("Act B");
        assertFalse(result);
    }
    @Test
    public void testTC3DeleteActivityFromEmptyList() {
        boolean result = this.emp.deleteActivity("Act B");
        assertFalse(result);
    }
}

