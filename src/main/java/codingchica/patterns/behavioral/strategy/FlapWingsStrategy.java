package codingchica.patterns.behavioral.strategy;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * A flying strategy that involves flapping wings.
 */
public class FlapWingsStrategy extends FlyingStrategy {
    /**
     * Retrieve the message to output when using the flying strategy.
     *
     * @return The message to use to indicate that the flying strategy involves flapping the wings.
     */
    @Override
    public String getFlyingMessage() {
        return "Flap wings and fly.";
    }

    /**
     * Generate a hashCode for this object.
     * @return An int value representing a hash of this object.
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * Compare two objects for equality.
     * @param obj The other object to use in the comparison.
     * @return Whether the two objects are equivalent.
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
