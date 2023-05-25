package codingchica.patterns.behavioral.strategy.model;

import codingchica.patterns.behavioral.strategy.FlyingStrategy;
import codingchica.patterns.behavioral.strategy.UnableToFlyStrategy;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Optional;

/**
 * A POJO representing an animal of some kind.
 */
@ToString
@Builder(builderClassName = "Builder")
@Getter
public class Animal {
    private ScientificClassification scientificClassification;
    private String name;
    private String description;
    @Setter
    private FlyingStrategy flyingStrategy;

    /**
     * Retrieve the message to present when the animal attempts to fly.
     *
     * @return The message associated with the animal's current flying strategy, or a default indicating the
     * inability to fly.
     */
    public String getFlyingMessage() {
        return getFlyingStrategy()
                .orElse(new UnableToFlyStrategy())
                .getFlyingMessage();
    }

    /**
     * Getter for the flying strategy.
     *
     * @return The Optional indicating the current flying strategy, if present.
     */
    public Optional<FlyingStrategy> getFlyingStrategy() {
        return Optional.ofNullable(flyingStrategy);
    }

    /**
     * Generate a hashCode for this object.
     *
     * @return An int value representing a hash of this object.
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * Compare two objects for equality.
     *
     * @param obj The other object to use in the comparison.
     * @return Whether the two objects are equivalent.
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
