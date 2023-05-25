package codingchica.patterns.behavioral.strategy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Unit tests for the codingchica.patterns.behavioral.strategy.AirplaneStrategy class */
class AirplaneStrategyTest {
    /** An instance of the class under test. */
    private AirplaneStrategy airplaneStrategy = new AirplaneStrategy();

    /** Unit tests for the getFlyingMessage method. */
    @Nested
    public class GetFlyingMessageTest {
        @Test
        public void getFlyingMessage_expectedValueReturned() {
            // Setup

            // Execution
            String result = airplaneStrategy.getFlyingMessage();

            // Validation
            assertEquals("Board an airplane and fly inside it.", result);
        }
    }
}