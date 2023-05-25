package codingchica.patterns.behavioral.strategy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Unit tests for the codingchica.patterns.behavioral.strategy.FlyingStrategy class */
class FlyingStrategyTest {
    /** An instance of the class under test. */
    private FlyingStrategy flyingStrategy = new TestFlyingStrategy();

    /** Unit tests for the hashCode method. */
    @Nested
    public class HashCodeTest {
        @Test
        public void hashCode_whenInvoked_returnsSame() {
            // Setup
            FlyingStrategy flyingStrategy2 = new TestFlyingStrategy();

            // Execution / Validation
            assertEquals(flyingStrategy.hashCode(), flyingStrategy2.hashCode());
        }
    }

    /** Unit tests for the equals method. */
    @Nested
    public class EqualsTest{

        @Test
        public void equals_whenInvokedWithSame_returnsTrue() {
            // Setup

            // Execution / Validation
            assertEquals(flyingStrategy, flyingStrategy);
        }

        @Test
        public void equals_whenInvokedWithEquivalent_returnsTrue() {
            // Setup
            FlyingStrategy flyingStrategy2 = new TestFlyingStrategy();

            // Execution / Validation
            assertEquals(flyingStrategy, flyingStrategy2);
        }
    }


    /** A concrete instance of FlyingStrategy, so we can test this class in isolation. */
    public class TestFlyingStrategy extends FlyingStrategy {
        @Override
        public String getFlyingMessage() {
            return "this is a test message";
        }
    };
}