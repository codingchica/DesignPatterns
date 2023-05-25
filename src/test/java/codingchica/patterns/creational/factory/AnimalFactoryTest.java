package codingchica.patterns.creational.factory;

import codingchica.patterns.behavioral.strategy.AirplaneStrategy;
import codingchica.patterns.behavioral.strategy.FlyingStrategy;
import codingchica.patterns.behavioral.strategy.GlidingStrategy;
import codingchica.patterns.behavioral.strategy.model.Animal;
import codingchica.patterns.behavioral.strategy.model.ScientificClassification;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/** Unit tests for the codingchica.patterns.creational.factory.AnimalFactory class */
class AnimalFactoryTest {
    private AnimalFactory animalFactory = AnimalFactory.getInstance();

    /** Unit tests for the getInstance method. */
    @Nested
    public class GetInstanceTest {
        @Test
        public void getInstance_whenInvoked_returnsSingleton(){
            // Setup
            assertNotNull(animalFactory, "animalFactory");

            // Execution
            AnimalFactory animalFactory2 = AnimalFactory.getInstance();

            // Validation
            assertNotNull(animalFactory2, "animalFactory2");
            assertSame(animalFactory, animalFactory2);
        }
    }

    /** Unit tests for the getHuman method. */
    @Nested
    public class GetHumanTest {
        @ParameterizedTest
        @ValueSource(strings = {"", "name", "name value goes here"})
        public void getHuman_whenNamePopulated_thenConsumed(String value){
            // Setup

            // Execution
            Animal result =animalFactory.getHuman(value, "description", true);

            // Validation
            assertEquals(value, result.getName());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "description", "description value goes here"})
        public void getHuman_whenDescriptionPopulated_thenConsumed(String value){
            // Setup

            // Execution
            Animal result =animalFactory.getHuman("name", value, true);

            // Validation
            assertEquals(value, result.getDescription());
        }

        @Test
        public void getHuman_whenInvoked_thenCorrectScientificClassification(){
            // Setup
            ScientificClassification scientificClassification = ScientificClassification.builder()
                    // https://en.wikipedia.org/wiki/Human_taxonomy
                    .kingdomName("Animalia")
                    .phylumName("Chordata")
                    .className("Mammalia")
                    .orderName("Primates")
                    .subOrderName("Haplorhini")
                    .infraOrder("Simiformes")
                    .familyName("Hominidae")
                    .subFamilyName("Homininae")
                    .tribeName("Hominini")
                    .genusName("Homo")
                    .speciesName("Homo sapiens")
                    .build();

            // Execution
            Animal result =animalFactory.getHuman("name", "description", true);

            // Validation
            assertEquals(scientificClassification, result.getScientificClassification());
        }

        @ParameterizedTest
        @ValueSource(booleans = {true, false})
        public void getHuman_whenIsAdultPopulated_thenCorrectFlyingStrategyConsumed(boolean value){
            // Setup
            FlyingStrategy expectedFlyingStrategy = new AirplaneStrategy();

            // Execution
            Animal result =animalFactory.getHuman("name", "description", value);

            // Validation
            Optional<FlyingStrategy> flyingStrategyOptional = result.getFlyingStrategy();
            if (value){
                assertTrue(flyingStrategyOptional.isPresent(), "isPresent");
                assertEquals(expectedFlyingStrategy, flyingStrategyOptional.get(), "flyingStrategy");
            } else {
                assertFalse(flyingStrategyOptional.isPresent(), "isPresent");
            }
        }
    }

    /** Unit tests for the getFlyingSquirrel method. */
    @Nested
    public class GetFlyingSquirrelTest {
        @ParameterizedTest
        @ValueSource(strings = {"", "name", "name value goes here"})
        public void getFlyingSquirrel_whenNamePopulated_thenConsumed(String value){
            // Setup

            // Execution
            Animal result =animalFactory.getFlyingSquirrel(value, "description", true);

            // Validation
            assertEquals(value, result.getName());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "description", "description value goes here"})
        public void getFlyingSquirrel_whenDescriptionPopulated_thenConsumed(String value){
            // Setup

            // Execution
            Animal result =animalFactory.getFlyingSquirrel("name", value, true);

            // Validation
            assertEquals(value, result.getDescription());
        }

        @Test
        public void getFlyingSquirrel_whenInvoked_thenCorrectScientificClassification(){
            // Setup
            ScientificClassification scientificClassification = ScientificClassification.builder()
                    // https://en.wikipedia.org/wiki/Flying_squirrel
                    .kingdomName("Animalia")
                    .phylumName("Chordata")
                    .className("Mammalia")
                    .orderName("Rodentia")
                    .familyName("Sciuridae")
                    .subFamilyName("Sciuridae")
                    .tribeName("Pteromyini")
                    .speciesName("Glaucomys sabrinus")
                    .build();

            // Execution
            Animal result =animalFactory.getFlyingSquirrel("name", "description", true);

            // Validation
            assertEquals(scientificClassification, result.getScientificClassification());
        }

        @ParameterizedTest
        @ValueSource(booleans = {true, false})
        public void getFlyingSquirrel_whenIsAdultPopulated_thenCorrectFlyingStrategyConsumed(boolean value){
            // Setup
            FlyingStrategy expectedFlyingStrategy = new GlidingStrategy();

            // Execution
            Animal result =animalFactory.getFlyingSquirrel("name", "description", value);

            // Validation
            Optional<FlyingStrategy> flyingStrategyOptional = result.getFlyingStrategy();
            if (value){
                assertTrue(flyingStrategyOptional.isPresent(), "isPresent");
                assertEquals(expectedFlyingStrategy, flyingStrategyOptional.get(), "flyingStrategy");
            } else {
                assertFalse(flyingStrategyOptional.isPresent(), "isPresent");
            }
        }
    }
}