package codingchica.patterns.behavioral.strategy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Unit tests for the codingchica.patterns.behavioral.strategy.FlapWingsStrategy class */
class FlapWingsStrategyTest {
    /** An instance of the class under test. */
    private FlapWingsStrategy flapWingsStrategy = new FlapWingsStrategy();

    /** Unit tests for the getFlyingMessage method. */
    @Nested
    public class GetFlyingMessageTest {
        @Test
        public void getFlyingMessage_expectedValueReturned() {
            // Setup

            // Execution
            String result = flapWingsStrategy.getFlyingMessage();

            // Validation
            assertEquals("Flap wings and fly.", result);
        }
    }

    /** Unit tests for the hashCode method. */
    @Nested
    public class HashCodeTest {
        @Test
        public void hashCode_whenInvoked_returnsSame() {
            // Setup
            FlapWingsStrategy flapWingsStrategy2 = new FlapWingsStrategy();

            // Execution / Validation
            assertEquals(flapWingsStrategy.hashCode(), flapWingsStrategy2.hashCode());
        }
    }

    /** Unit tests for the equals method. */
    @Nested
    public class EqualsTest{

        @Test
        public void equals_whenInvokedWithSame_returnsTrue() {
            // Setup

            // Execution / Validation
            assertEquals(flapWingsStrategy, flapWingsStrategy);
        }

        @Test
        public void equals_whenInvokedWithEquivalent_returnsTrue() {
            // Setup
            FlapWingsStrategy flapWingsStrategy2 = new FlapWingsStrategy();

            // Execution / Validation
            assertEquals(flapWingsStrategy, flapWingsStrategy2);
        }
    }
}