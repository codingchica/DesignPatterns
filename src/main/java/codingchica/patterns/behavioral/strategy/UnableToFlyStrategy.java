package codingchica.patterns.behavioral.strategy;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * A strategy that indicates the inability to fly.
 */
public class UnableToFlyStrategy extends FlyingStrategy {
    /**
     * Retrieve the message to output when using the flying strategy.
     *
     * @return A String indicating the inability to fly.
     */
    @Override
    public String getFlyingMessage() {
        return "Unable to fly";
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
