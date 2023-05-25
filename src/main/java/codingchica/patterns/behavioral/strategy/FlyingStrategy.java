package codingchica.patterns.behavioral.strategy;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The interface for different strategies to fly.
 */
public abstract class FlyingStrategy {
    /** Used to reduce hashCode collisions between subClasses. */
    private String className = this.getClass().getCanonicalName();

    /**
     * In this example, we are just outputting a message indicating which strategy was chosen.
     * However, this could be very complex business logic, such as might be used as a new feature is being slowly
     * rolled out to more and more of the user base, such as percentage-based feature flags.
     *
     * @return A message to indicate which flying strategy was chosen.
     */
    public abstract String getFlyingMessage();


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
