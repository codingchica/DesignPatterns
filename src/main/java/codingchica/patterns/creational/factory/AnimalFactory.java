package codingchica.patterns.creational.factory;

import codingchica.patterns.behavioral.strategy.AirplaneStrategy;
import codingchica.patterns.behavioral.strategy.GlidingStrategy;
import codingchica.patterns.behavioral.strategy.model.Animal;
import codingchica.patterns.behavioral.strategy.model.ScientificClassification;

import java.util.Map;
import java.util.TreeMap;

/**
 * A factory to create Animals of different types.
 */
public class AnimalFactory {
    private static final AnimalFactory animalFactory;
    private static final Map<String, ScientificClassification> SCIENTIFIC_CLASSIFICATION_MAP = new TreeMap<>();
    private static final String COMMON_NAME_HUMAN = "Person";
    private static final String COMMON_NAME_FLYING_SQUIRREL = "Flying Squirrel";

    static {
        animalFactory = new AnimalFactory();
        SCIENTIFIC_CLASSIFICATION_MAP.put(COMMON_NAME_HUMAN, ScientificClassification.builder()
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
                .build());

        SCIENTIFIC_CLASSIFICATION_MAP.put(COMMON_NAME_FLYING_SQUIRREL, ScientificClassification.builder()
                // https://en.wikipedia.org/wiki/Flying_squirrel
                .kingdomName("Animalia")
                .phylumName("Chordata")
                .className("Mammalia")
                .orderName("Rodentia")
                .familyName("Sciuridae")
                .subFamilyName("Sciuridae")
                .tribeName("Pteromyini")
                .speciesName("Glaucomys sabrinus")
                .build());

    }

    /**
     * Get an instance of the AnimalFactory class.
     *
     * @return An AnimalFactory object.
     */
    public static AnimalFactory getInstance() {
        return animalFactory;
    }

    /**
     * Private constructor, so that we can enforce singleton approach.
     *
     * @see #getInstance()
     */
    private AnimalFactory() {
    }

    /**
     * Get an instance of a human animal / person.
     *
     * @param name        The name of the human.
     * @param description A description of the human.
     * @param isAdult     Whether the human is an adult.
     * @return An initialized Animal instance for the human.
     */
    public Animal getHuman(String name, String description, boolean isAdult) {
        Animal.Builder builder = Animal.builder()
                .scientificClassification(SCIENTIFIC_CLASSIFICATION_MAP.get(COMMON_NAME_HUMAN))
                .name(name)
                .description(description);
        if (isAdult) {
            builder.flyingStrategy(new AirplaneStrategy());
        }
        return builder.build();
    }

    /**
     * Get an instance of a flying squirrel.
     *
     * @param name        The name of the flying squirrel.
     * @param description A description of the flying squirrel.
     * @param isAdult     Whether the flying squirrel is an adult.
     * @return An initialized Animal instance for the flying squirrel.
     */
    public Animal getFlyingSquirrel(String name, String description, boolean isAdult) {
        Animal.Builder builder = Animal.builder()
                .scientificClassification(SCIENTIFIC_CLASSIFICATION_MAP.get(COMMON_NAME_FLYING_SQUIRREL))
                .name(name)
                .description(description);
        if (isAdult) {
            builder.flyingStrategy(new GlidingStrategy());
        }
        return builder.build();
    }
}
