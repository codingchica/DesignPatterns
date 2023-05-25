package codingchica.patterns.behavioral.strategy.model;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * A POJO representing the scientific classification of a given object.
 */
@ToString
@Builder(builderClassName = "Builder")
@Getter
public class ScientificClassification {
    @NonNull
    private String kingdomName;
    @NonNull
    private String phylumName;
    @NonNull
    private String className;
    @NonNull
    private String orderName;
    private String subOrderName;
    private String infraOrder;
    @NonNull
    private String speciesName;
    @NonNull
    private String familyName;
    @NonNull
    private String subFamilyName;
    @NonNull
    private String tribeName;
    private String genusName;

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
