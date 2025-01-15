import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class WeatherImpactCalculatorTest {

    @Test
    public void testNormalConditions() {
        // Test for normal weather conditions
        String input = "1000\n20\n5\nno\nno\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        WeatherImpactCalculator.main(new String[]{});
        // Check console output or other side effects as needed
    }

    @Test
    public void testHeavySnowfall() {
        // Test for heavy snow
        String input = "1000\n20\n35\nno\nno\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        WeatherImpactCalculator.main(new String[]{});
        // Check console output or other side effects as needed
    }

    @Test
    public void testHighWindSpeed() {
        // Test for high wind 
        String input = "1000\n70\n5\nno\nno\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        WeatherImpactCalculator.main(new String[]{});
        // Check console output or other side effects as needed
    }

    @Test
    public void testInvalidInput() {
        // Test fr invalid input
        String input = "1000\nabc\n5\nno\nno\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThrows(Exception.class, () -> {
            WeatherImpactCalculator.main(new String[]{});
        });
    }

    @Test
    public void testUnreasonableNumbers() {
        // for unreasonable numbers
        String input = "1000000\n20\n5\nno\nno\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        WeatherImpactCalculator.main(new String[]{});
        // Check console output or other side effects as needed
    }
}
