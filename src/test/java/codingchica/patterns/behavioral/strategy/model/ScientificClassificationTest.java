package codingchica.patterns.behavioral.strategy.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/** Unit tests for the codingchica.patterns.behavioral.strategy.model.ScientificClassification. */
@Slf4j
class ScientificClassificationTest {
    private final ScientificClassification.Builder builder = ScientificClassification.builder();

    /**
     * An enum listing all the expected fields in the Animal class, as well as some additional details needed for testing.
     */
    @Getter
    private enum ScientificClassificationField {
        KINGDOM(null, false, String.class, false, "kingdom", "kingdomValueGoesHere", "setKingdom"),
        PHYLUM(null, false, String.class, false, "phylum", "phylumValueGoesHere", "setPhylum"),
        CLASS_NAME(null, false, String.class, false, "class", "classValueGoesHere", "setClass"),
        ORDER_NAME(null, false, String.class, false, "order", "orderValueGoesHere", "setOrder"),
        SPECIES_NAME(null, false, String.class, false, "species", "speciesValueGoesHere", "setSpecies"),
        FAMILY_NAME(null, false, String.class, false, "family", "familValueGoesHere", "setFamily"),
        SUB_FAMILY_NAME(null, false, String.class, false, "subFamily", "subFamilyValueGoesHere", "setSubFamily"),
        TRIBAL_NAME(null, false, String.class, false, "tribe", "tribeGoesHere", "setTribe"),
        GENUS_NAME(null, true, String.class, false, "genus", "genusGoesHere", "setGenus");

        final Object defaultBuilderValue;
        final Object defaultPopulatedValue;
        final Object modifiedPopulatedValue;
        final String setterMethodName;
        final Class fieldClass;
        final boolean nullable;
        final boolean setter;


        ScientificClassificationField(Object defaultBuilderValue, boolean nullable, Class fieldClass, boolean setter, Object defaultPopulatedValue, Object modifiedPopulatedValue, String setterMethodName) {
            this.defaultBuilderValue = defaultBuilderValue;
            this.nullable = nullable;
            this.fieldClass = fieldClass;
            this.setter = setter;
            this.defaultPopulatedValue = defaultPopulatedValue;
            this.modifiedPopulatedValue = modifiedPopulatedValue;
            this.setterMethodName = setterMethodName;
        }

        public void setFieldOnBuilder(@NonNull ScientificClassification.Builder builder) {
            log.info(this.setterMethodName + "("+ defaultPopulatedValue+");");
            switch (this) {
                case KINGDOM -> builder.kingdomName((String) defaultPopulatedValue);
                case PHYLUM -> builder.phylumName((String) defaultPopulatedValue);
                case CLASS_NAME -> builder.className((String) defaultPopulatedValue);
                case ORDER_NAME -> builder.orderName((String) defaultPopulatedValue);
                case SPECIES_NAME -> builder.speciesName((String) defaultPopulatedValue);
                case FAMILY_NAME -> builder.familyName((String) defaultPopulatedValue);
                case SUB_FAMILY_NAME -> builder.subFamilyName((String) defaultPopulatedValue);
                case TRIBAL_NAME -> builder.tribeName((String) defaultPopulatedValue);
                case GENUS_NAME -> builder.genusName((String) defaultPopulatedValue);
                default -> throw new IllegalArgumentException("Unknown field: " + this);
            }
        }

        public void setField(@NonNull ScientificClassification.Builder builder, Object value) {
            log.info(this.setterMethodName + "("+ value+");");
            switch (this) {
                case KINGDOM -> builder.kingdomName((String) value);
                case PHYLUM -> builder.phylumName((String) value);
                case CLASS_NAME -> builder.className((String) value);
                case ORDER_NAME -> builder.orderName((String) value);
                case SPECIES_NAME -> builder.speciesName((String) value);
                case FAMILY_NAME -> builder.familyName((String) value);
                case SUB_FAMILY_NAME -> builder.subFamilyName((String) value);
                case TRIBAL_NAME -> builder.tribeName((String) value);
                case GENUS_NAME -> builder.genusName((String) value);
                default -> throw new IllegalArgumentException("Unknown field: " + this);
            }
        }

        public void setField(@NonNull ScientificClassification scientificClassification, Object value) {
            log.info(this.setterMethodName + "("+ value+");");
            throw new IllegalArgumentException("Unknown field: " + this);
        }

        private Object getActualValue(@NonNull ScientificClassification scientificClassification) {
            Object value;
            switch (this) {
                case KINGDOM -> value = scientificClassification.getKingdomName();
                case PHYLUM -> value = scientificClassification.getPhylumName();
                case CLASS_NAME -> value = scientificClassification.getClassName();
                case ORDER_NAME -> value = scientificClassification.getOrderName();
                case SPECIES_NAME -> value = scientificClassification.getSpeciesName();
                case FAMILY_NAME -> value = scientificClassification.getFamilyName();
                case SUB_FAMILY_NAME -> value = scientificClassification.getSubFamilyName();
                case TRIBAL_NAME -> value = scientificClassification.getTribeName();
                case GENUS_NAME -> value = scientificClassification.getGenusName();
                default -> throw new IllegalArgumentException("Unknown field: " + this);
            }
            return value;
        }
    }

    private void populateAllFields(@NonNull ScientificClassification.Builder builder) {
        Arrays.stream(ScientificClassificationField.values()).forEach(item -> item.setFieldOnBuilder(builder));
    }

    private void populateAllFieldsExcept(@NonNull ScientificClassification.Builder builder, @NonNull ScientificClassificationField field) {
        Arrays.stream(ScientificClassificationField.values())
                .filter(item -> !field.equals(item))
                .forEach(item -> item.setFieldOnBuilder(builder));
    }

    /**
     * Unit tests for the builder.
     */
    @Nested
    public class BuilderTest {

        /**
         * Unit tests exercising the default values/behavior when none provided to the builder.
         */
        @Nested
        public class DefaultValueTest {
            @ParameterizedTest
            @EnumSource(ScientificClassificationField.class)
            public void build_whenNonNullableFieldDefaultsToNull_thenExceptionThrown(ScientificClassificationField value) {
                // Setup
                assumeFalse(value.nullable);
                populateAllFieldsExcept(builder, value);

                // Execution
                Executable executable = builder::build;

                // Validation
                assertThrows(NullPointerException.class, executable, value.name());
            }


            @ParameterizedTest
            @EnumSource(ScientificClassificationField.class)
            public void build_whenNullableFieldDefaultsToNull_thenAnimalBuilt(ScientificClassificationField value) {
                // Setup
                assumeTrue(value.nullable);
                populateAllFieldsExcept(builder, value);
                value.setField(builder, null);

                // Execution
               ScientificClassification classification = builder.build();

                // Validation
                assertNull(value.getActualValue(classification), value.name());
            }
        }

        /**
         * Unit tests exercising the build method.
         */
        @Nested
        public class BuildTest {

            @ParameterizedTest
            @EnumSource(ScientificClassificationField.class)
            public void build_whenRequiredFieldPopulated_thenGetterReturnsExpectedValue(ScientificClassificationField value) {
                // Setup
                assumeFalse(value.nullable);
                populateAllFields(builder);

                // Execution
               ScientificClassification classification = builder.build();

                // Validation
                assertEquals(value.getDefaultPopulatedValue(), value.getActualValue(classification), value.name());
            }

            @ParameterizedTest
            @EnumSource(ScientificClassificationField.class)
            public void build_whenOptionalFieldPopulated_thenOptionalValueReturned(ScientificClassificationField value) {
                // Setup
                assumeTrue(value.nullable);
                populateAllFields(builder);

                // Execution
               ScientificClassification classification = builder.build();

                // Validation
                assertEquals(value.getDefaultPopulatedValue(), value.getActualValue(classification), value.name());
            }
        }

        /**
         * Unit tests exercising setter behavior on the builder.
         */
        @Nested
        public class SetterTest {
            @ParameterizedTest
            @EnumSource(ScientificClassificationField.class)
            public void build_whenOptionalFieldSetToNull_thenOptionalEmptyReturned(ScientificClassificationField value) {
                // Setup
                assumeTrue(value.nullable);
                populateAllFields(builder);
                value.setField(builder, null);

                // Execution
               ScientificClassification classification = builder.build();

                // Validation
                assertNull(value.getActualValue(classification), value.name());
            }

            @ParameterizedTest
            @EnumSource(ScientificClassificationField.class)
            public void build_whenNonNullableFieldSetToNull_thenExceptionThrown(ScientificClassificationField value) {
                // Setup
                assumeFalse(value.nullable);
                populateAllFields(builder);

                // Execution
                Executable executable = () -> value.setField(builder, null);

                // Validation
                assertThrows(NullPointerException.class, executable, value.name());
            }
        }

        /**
         * Unit tests exercising Animal.Builder.toString method.
         */
        @Nested
        public class ToStringTest {
            @Test
            public void toString_whenInvoked_thenReturnsExpectedFormat() {
                // Setup

                // Execution
                String result = builder.toString();

                // Validation
                assertEquals("ScientificClassification.Builder(kingdomName=null, phylumName=null, className=null, orderName=null, subOrderName=null, infraOrder=null, speciesName=null, familyName=null, subFamilyName=null, tribeName=null, genusName=null)", result);
            }
        }
    }

    /**
     * Unit tests for the getters and setters.
     */
    @Nested
    public class GetterSetterTest {
        @ParameterizedTest
        @EnumSource(ScientificClassificationField.class)
        public void animal_whenSetterInvoked_thenGetterReturnsOptionalModifiedValue(ScientificClassificationField value) {
            // Setup
            assumeTrue(value.setter);
            populateAllFields(builder);
           ScientificClassification classification = builder.build();
            Object newValue = value.getModifiedPopulatedValue();

            // Execution
            value.setField(classification, newValue);

            // Validation
            assertEquals(Optional.of(newValue), value.getActualValue(classification), value.name());
        }

        @ParameterizedTest
        @EnumSource(ScientificClassificationField.class)
        public void animal_whenExamined_setterDoesNotExist(ScientificClassificationField value) {
            // Setup
            assumeFalse(value.setter);

            // Execution
            Optional<Method> result = ReflectionUtils.findMethod(Animal.class, value.getSetterMethodName(), value.getFieldClass());

            // Validation
            assertEquals(Optional.empty(), result, value.name());
        }

        @ParameterizedTest
        @EnumSource(ScientificClassificationField.class)
        public void animal_whenExamined_setterExists(ScientificClassificationField value) {
            // Setup
            assumeTrue(value.setter);

            // Execution
            Optional<Method> result = ReflectionUtils.findMethod(Animal.class, value.getSetterMethodName(), value.getFieldClass());

            // Validation
            assertTrue(result.isPresent(), value.name());
        }
    }

    /**
     * Unit tests exercising Animal.toString method.
     */
    @Nested
    public class ToStringTest {
        @Test
        public void toString_whenInvoked_thenReturnsExpectedFormat() {
            // Setup
            Arrays.stream(ScientificClassificationField.values())
                    .filter(item -> !item.nullable)
                    .forEach(item -> item.setFieldOnBuilder(builder));
           ScientificClassification classification = builder.build();

            // Execution
            String result = classification.toString();

            // Validation
            assertEquals("ScientificClassification(kingdomName=kingdom, phylumName=phylum, className=class, orderName=order, subOrderName=null, infraOrder=null, speciesName=species, familyName=family, subFamilyName=subFamily, tribeName=tribe, genusName=null)", result);
        }
    }
}