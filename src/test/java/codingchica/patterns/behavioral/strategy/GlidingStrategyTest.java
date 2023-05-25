package codingchica.patterns.behavioral.strategy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Unit tests for the codingchica.patterns.behavioral.strategy.GlidingStrategy class */
class GlidingStrategyTest {
    /** An instance of the class under test. */
    private GlidingStrategy glidingStrategy = new GlidingStrategy();

    /** Unit tests for the getFlyingMessage method. */
    @Nested
    public class GetFlyingMessageTest {
        @Test
        public void getFlyingMessage_expectedValueReturned() {
            // Setup

            // Execution
            String result = glidingStrategy.getFlyingMessage();

            // Validation
            assertEquals("Spread wings and glide.", result);
        }
    }


    /** Unit tests for the hashCode method. */
    @Nested
    public class HashCodeTest {
        @Test
        public void hashCode_whenInvoked_returnsSame() {
            // Setup
            GlidingStrategy glidingStrategy2 = new GlidingStrategy();

            // Execution / Validation
            assertEquals(glidingStrategy.hashCode(), glidingStrategy2.hashCode());
        }
    }

    /** Unit tests for the equals method. */
    @Nested
    public class EqualsTest{

        @Test
        public void equals_whenInvokedWithSame_returnsTrue() {
            // Setup

            // Execution / Validation
            assertEquals(glidingStrategy, glidingStrategy);
        }

        @Test
        public void equals_whenInvokedWithEquivalent_returnsTrue() {
            // Setup
            GlidingStrategy glidingStrategy2 = new GlidingStrategy();

            // Execution / Validation
            assertEquals(glidingStrategy, glidingStrategy2);
        }
    }
}