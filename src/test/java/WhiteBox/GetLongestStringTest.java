package WhiteBox;

import com.example.Report;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetLongestStringTest {

    @Test
    public void testA() {
        String[] input = new String[] {};
        assertEquals(0, Report.getLongestStringLength(input));
    }

    @Test
    public void testB() {
        String[] input = new String[] {"Project Leader: bap"};
        assertEquals(19, Report.getLongestStringLength(input));
    }

    @Test
    public void testC() {
        String[] input = new String[] {"Project Leader: bap", "Total hours registered: 20"};
        assertEquals(26, Report.getLongestStringLength(input));
    }

    @Test
    public void testD() {
        String[] input = new String[] {"Total hours registered: 20", "Project Leader: bap"};
        assertEquals(26, Report.getLongestStringLength(input));
    }
}
