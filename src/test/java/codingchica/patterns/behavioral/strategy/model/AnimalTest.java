package codingchica.patterns.behavioral.strategy.model;

import codingchica.patterns.behavioral.strategy.AirplaneStrategy;
import codingchica.patterns.behavioral.strategy.FlapWingsStrategy;
import codingchica.patterns.behavioral.strategy.FlyingStrategy;
import codingchica.patterns.behavioral.strategy.UnableToFlyStrategy;
import lombok.Builder;
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
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the codingchica.patterns.behavioral.strategy.model.Animal class
 */
@Slf4j
class AnimalTest {
    private final Animal.Builder animalBuilder = Animal.builder();

    /**
     * An enum listing all the expected fields in the Animal class, as well as some additional details needed for testing.
     */
    @Getter
    private enum AnimalField {
        SCIENTIFIC_CLASSIFICATION(null, true, ScientificClassification.class, false,
                ScientificClassification.builder()
                        .kingdomName("kingdomName1")
                        .phylumName("phylumName1")
                        .className("className1")
                        .orderName("orderName1")
                        .speciesName("speciesName1")
                        .familyName("familyName1")
                        .subFamilyName("subFamilyName1")
                        .tribeName("tribeName1")
                        .genusName("genusName1")
                        .build(),
                ScientificClassification.builder()
                        .kingdomName("kingdomName2")
                        .phylumName("phylumName2")
                        .className("className2")
                        .orderName("orderName2")
                        .speciesName("speciesName2")
                        .familyName("familyName2")
                        .tribeName("tribeName2")
                        .subFamilyName("subFamilyName2")
                        .genusName("genusName2")
                        .build(), "setScientificClassification"),
        NAME(null, true, String.class, false, "name", "nameGoesHere", "setName"),
        DESCRIPTION(null, true, String.class, false, "description", "descriptionGoesHere", "setDescription"),
        FLYING_STRATEGY(null, true, FlyingStrategy.class, true, new UnableToFlyStrategy(), new AirplaneStrategy(), "setFlyingStrategy");

        final Object defaultBuilderValue;
        final Object defaultPopulatedValue;
        final Object modifiedPopulatedValue;
        final String setterMethodName;
        final Class fieldClass;
        final boolean nullable;
        final boolean setter;


        AnimalField(Object defaultBuilderValue, boolean nullable, Class fieldClass, boolean setter, Object defaultPopulatedValue, Object modifiedPopulatedValue, String setterMethodName) {
            this.defaultBuilderValue = defaultBuilderValue;
            this.nullable = nullable;
            this.fieldClass = fieldClass;
            this.setter = setter;
            this.defaultPopulatedValue = defaultPopulatedValue;
            this.modifiedPopulatedValue = modifiedPopulatedValue;
            this.setterMethodName = setterMethodName;
        }

        public void setFieldOnBuilder(@NonNull Animal.Builder builder) {
            log.info("setFieldOnBuilder: " + this.setterMethodName + "(" + defaultBuilderValue + ");");
            switch (this) {
                case SCIENTIFIC_CLASSIFICATION -> builder.scientificClassification((ScientificClassification) defaultPopulatedValue);
                case NAME -> builder.name((String) defaultPopulatedValue);
                case DESCRIPTION -> builder.description((String) defaultPopulatedValue);
                case FLYING_STRATEGY -> builder.flyingStrategy((FlyingStrategy) defaultPopulatedValue);
                default -> throw new IllegalArgumentException("Unknown field: " + this);
            }
        }

        public void setField(@NonNull Animal.Builder builder, Object value) {
            log.info("setField: " + this.setterMethodName + "(" + value + ");");
            switch (this) {
                case SCIENTIFIC_CLASSIFICATION -> builder.scientificClassification((ScientificClassification) value);
                case NAME -> builder.name((String) value);
                case DESCRIPTION -> builder.description((String) value);
                case FLYING_STRATEGY -> builder.flyingStrategy((FlyingStrategy) value);
                default -> throw new IllegalArgumentException("Unknown field: " + this);
            }
        }

        public void setField(@NonNull Animal animal, Object value) {
            log.info("setField: " + this.setterMethodName + "(" + value + ");");
            switch (this) {
                case FLYING_STRATEGY -> animal.setFlyingStrategy((FlyingStrategy) value);
                default -> throw new IllegalArgumentException("Unknown field: " + this);
            }
        }

        private Object getActualValue(@NonNull Animal animal) {
            Object value;
            switch (this) {
                case SCIENTIFIC_CLASSIFICATION -> value = animal.getScientificClassification();
                case NAME -> value = animal.getName();
                case DESCRIPTION -> value = animal.getDescription();
                case FLYING_STRATEGY -> value = animal.getFlyingStrategy();
                default -> throw new IllegalArgumentException("Unknown field: " + this);
            }
            return value;
        }
    }

    private void populateAllFields(@NonNull Animal.Builder builder) {
        Arrays.stream(AnimalField.values()).forEach(item -> item.setField(builder, item.defaultPopulatedValue));
    }
    private void populateRequiredFields(@NonNull Animal.Builder builder) {
        Arrays.stream(AnimalField.values())
                .filter(item -> !item.nullable)
                .forEach(item -> item.setField(builder, item.defaultPopulatedValue));
    }

    private void populateAllFieldsExcept(@NonNull Animal.Builder builder, @NonNull AnimalField field) {
        Arrays.stream(AnimalField.values())
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
            @EnumSource(AnimalField.class)
            public void build_whenNonNullableFieldDefaultsToNull_thenExceptionThrown(AnimalField value) {
                // Setup
                assumeFalse(value.nullable);
                populateAllFieldsExcept(animalBuilder, value);

                // Execution
                Executable executable = animalBuilder::build;

                // Validation
                assertThrows(NullPointerException.class, executable, value.name());
            }


            @ParameterizedTest
            @EnumSource(AnimalField.class)
            public void build_whenNullableFieldDefaultsToNull_thenAnimalBuilt(AnimalField value) {
                // Setup
                assumeTrue(value.nullable);
                populateAllFieldsExcept(animalBuilder, value);
                value.setField(animalBuilder, null);

                // Execution
                Animal animal = animalBuilder.build();

                // Validation
                Object result = value.getActualValue(animal);
                if (result instanceof Optional<?>) {
                    assertEquals(Optional.empty(), result, "Optional: "+value.name());
                } else {
                    assertNull(result, value.name());
                }
            }
        }

        /**
         * Unit tests exercising the build method.
         */
        @Nested
        public class BuildTest {

            @ParameterizedTest
            @EnumSource(AnimalField.class)
            public void build_whenRequiredFieldPopulated_thenGetterReturnsExpectedValue(AnimalField value) {
                // Setup
                assumeFalse(value.nullable);
                populateAllFields(animalBuilder);

                // Execution
                Animal animal = animalBuilder.build();

                // Validation
                assertEquals(value.getDefaultPopulatedValue(), value.getActualValue(animal), value.name());
            }

            @ParameterizedTest
            @EnumSource(AnimalField.class)
            public void build_whenOptionalFieldPopulated_thenOptionalValueReturned(AnimalField value) {
                // Setup
                assumeTrue(value.nullable);
                populateAllFields(animalBuilder);
                Object expectedValue = value.getDefaultPopulatedValue();

                // Execution
                Animal animal = animalBuilder.build();

                // Validation

                Object result = value.getActualValue(animal);
                if (result instanceof Optional<?>) {
                    assertEquals(Optional.of(value.getDefaultPopulatedValue()), result, "Optional:"+value.name());
                } else {
                    assertEquals(value.getDefaultPopulatedValue(), result, "Optional:"+value.name());
                }

            }
        }

        /**
         * Unit tests exercising setter behavior on the builder.
         */
        @Nested
        public class SetterTest {
            @ParameterizedTest
            @EnumSource(AnimalField.class)
            public void build_whenOptionalFieldSetToNull_thenOptionalEmptyReturned(AnimalField value) {
                // Setup
                assumeTrue(value.nullable);
                populateAllFields(animalBuilder);
                value.setField(animalBuilder, null);

                // Execution
                Animal animal = animalBuilder.build();

                // Validation
                Object result = value.getActualValue(animal);
                if (result instanceof Optional<?>) {
                    assertEquals(Optional.empty(), result, "Optional: "+value.name());
                } else {
                    assertNull(result, value.name());
                }
            }

            @ParameterizedTest
            @EnumSource(AnimalField.class)
            public void build_whenNonNullableFieldSetToNull_thenExceptionThrown(AnimalField value) {
                // Setup
                assumeFalse(value.nullable);
                populateAllFields(animalBuilder);

                // Execution
                Executable executable = () -> value.setField(animalBuilder, null);

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
                String result = animalBuilder.toString();

                // Validation
                assertEquals("Animal.Builder(scientificClassification=null, name=null, description=null, flyingStrategy=null)", result);
            }
        }
    }

    /**
     * Unit tests for the getters and setters.
     */
    @Nested
    public class GetterSetterTest {
        @ParameterizedTest
        @EnumSource(AnimalField.class)
        public void animal_whenSetterInvoked_thenGetterReturnsOptionalModifiedValue(AnimalField value) {
            // Setup
            assumeTrue(value.setter);
            populateAllFields(animalBuilder);
            Animal animal = animalBuilder.build();
            Object newValue = value.getModifiedPopulatedValue();

            // Execution
            value.setField(animal, newValue);

            // Validation
            assertEquals(Optional.of(newValue), value.getActualValue(animal), value.name());
        }

        @ParameterizedTest
        @EnumSource(AnimalField.class)
        public void animal_whenExamined_setterDoesNotExist(AnimalField value) {
            // Setup
            assumeFalse(value.setter);

            // Execution
            Optional<Method> result = ReflectionUtils.findMethod(Animal.class, value.getSetterMethodName(), value.getFieldClass());

            // Validation
            assertEquals(Optional.empty(), result, value.name());
        }

        @ParameterizedTest
        @EnumSource(AnimalField.class)
        public void animal_whenExamined_setterExists(AnimalField value) {
            // Setup
            assumeTrue(value.setter);

            // Execution
            Optional<Method> result = ReflectionUtils.findMethod(Animal.class, value.getSetterMethodName(), value.getFieldClass());

            // Validation
            assertTrue(result.isPresent(), value.name());
        }
    }

    /**
     * Unit tests for the getFlyingMessage method
     */
    @Nested
    public class GetFlyingMessageTest {
        @Test
        public void getFlyingMessage_whenNull_returnsDefault() {
            // Setup
            populateAllFieldsExcept(animalBuilder, AnimalField.FLYING_STRATEGY);
            Animal animal = animalBuilder.build();
            FlyingStrategy expectedFlyingStrategy = new UnableToFlyStrategy();


            // Execution
            String result = animal.getFlyingMessage();

            // Validation
            assertEquals(expectedFlyingStrategy.getFlyingMessage(), result);
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
            Arrays.stream(AnimalField.values())
                    .filter(item -> !item.nullable)
                    .forEach(item -> item.setFieldOnBuilder(animalBuilder));
            Animal animal = animalBuilder.build();

            // Execution
            String result = animal.toString();

            // Validation
            assertEquals("Animal(scientificClassification=null, name=null, description=null, flyingStrategy=Optional.empty)", result);
        }
    }

    @Nested
    public class EqualsAndHashCodesTest {

        @Nested
        public class NullFieldsTest {
            @ParameterizedTest
            @EnumSource(AnimalField.class)
            public void animal_whenOtherAnimalReturnsFieldNull_thenNotEqual(AnimalField value) {
                // Setup
                populateAllFields(animalBuilder);
                Animal animal1 = animalBuilder.build();
                Animal animal2 = spy(animalBuilder.build());
                switch (value) {
                    case SCIENTIFIC_CLASSIFICATION -> when(animal2.getScientificClassification()).thenReturn(null);
                    case NAME -> when(animal2.getName()).thenReturn(null);
                    case DESCRIPTION -> when(animal2.getDescription()).thenReturn(null);
                    case FLYING_STRATEGY -> when(animal2.getFlyingStrategy()).thenReturn(null);
                    default -> throw new IllegalArgumentException("Unknown field: " + this);
                }

                // Execution

                // Validation
                assertFalse(animal1.equals(animal2), "animal1.equals(animal2)");
                assertFalse(animal2.equals(animal1), "animal2.equals(animal1)");
                assertNotEquals(animal1.hashCode(), animal2.hashCode(), "hashCode");
            }

            @ParameterizedTest
            @EnumSource(AnimalField.class)
            public void animal_whenThisAnimalReturnsFieldNull_thenNotEqual(AnimalField value) {
                // Setup
                populateRequiredFields(animalBuilder);
                Animal animal1 = animalBuilder.build();
                populateAllFields(animalBuilder);
                Animal animal2 = animalBuilder.build();

                // Execution

                // Validation
                assertFalse(animal1.equals(animal2), "equals");
                assertNotEquals(animal1.hashCode(), animal2.hashCode(), "hashCode");
            }
        }

        @Nested
        public class ObjectLevelTest {
            private class Bird extends Animal {
                Bird(@NonNull ScientificClassification scientificClassification, @NonNull String name, @NonNull String description, FlyingStrategy flyingStrategy) {
                    super(scientificClassification, name, description, flyingStrategy);
                }
            }

            @Test
            public void animal_whenOtherObjectExtendsAnimal_thenNotEqual() {
                // Setup
                populateAllFields(animalBuilder);
                Animal animal = animalBuilder.build();
                Bird bird = new Bird(animal.getScientificClassification(), "bird", "a wild bird", new FlapWingsStrategy());

                // Execution

                // Validation
                assertFalse(animal.equals(bird), "animal.equals(bird)");
                assertFalse(bird.equals(animal), "bird.equals(animal)");
            }

            @Test
            public void animal_whenOtherObjectNotAnimal_thenNotEqual() {
                // Setup
                populateAllFields(animalBuilder);
                Animal animal = animalBuilder.build();

                // Execution

                // Validation
                assertFalse(animal.equals("Some other class"), "animal.equals(String)");
            }

            @Test
            public void animal_whenOtherObjectNull_thenNotEqual() {
                // Setup
                populateAllFields(animalBuilder);

                // Execution
                Animal animal = animalBuilder.build();

                // Validation
                assertFalse(animal.equals(null), "equals");
            }

            @Test
            public void animal_whenObjectsSame_thenEqual() {
                // Setup
                populateAllFields(animalBuilder);

                // Execution
                Animal animal = animalBuilder.build();

                // Validation
                assertTrue(animal.equals(animal), "equals");
                assertEquals(animal.hashCode(), animal.hashCode(), "hashCode");
            }
        }

        @Test
        public void animal_whenFieldValuesSame_thenEqual() {
            // Setup
            populateAllFields(animalBuilder);
            Animal animal1 = animalBuilder.build();

            // Execution
            Animal animal2 = animalBuilder.build();

            // Validation
            assertEquals(animal1, animal2, "animal1.equals(animal2)");
            assertEquals(animal2, animal1, "animal2.equals(animal1)");
            assertEquals(animal1.hashCode(), animal2.hashCode(), "hashCode");
        }

        @ParameterizedTest
        @EnumSource(AnimalField.class)
        public void animal_whenFieldValuesDiffer_thenNotEqualAndHashCodesDiffer(AnimalField value) {
            // Setup
            populateAllFields(animalBuilder);
            Animal animal1 = animalBuilder.build();

            value.setField(animalBuilder, value.getModifiedPopulatedValue());
            assertNotEquals(value.getActualValue(animal1), value.getModifiedPopulatedValue());

            // Execution
            Animal animal2 = animalBuilder.build();

            // Validation
            assertNotEquals(animal1, animal2, "animal1.equals(animal2)");
            assertNotEquals(animal2, animal1, "animal2.equals(animal1)");
            assertNotEquals(animal1.hashCode(), animal2.hashCode(), "hashCode");
        }
    }
}