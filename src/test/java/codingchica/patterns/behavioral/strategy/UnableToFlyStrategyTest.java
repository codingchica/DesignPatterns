package codingchica.patterns.behavioral.strategy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Unit tests for the codingchica.patterns.behavioral.strategy.UnableToFlyStrategy class */
class UnableToFlyStrategyTest {
    /** An instance of the class under test. */
    private UnableToFlyStrategy unableToFlyStrategy = new UnableToFlyStrategy();

    /** Unit tests for the getFlyingMessage method. */
    @Nested
    public class GetFlyingMessageTest {
        @Test
        public void getFlyingMessage_expectedValueReturned() {
            // Setup

            // Execution
            String result = unableToFlyStrategy.getFlyingMessage();

            // Validation
            assertEquals("Unable to fly", result);
        }
    }

}