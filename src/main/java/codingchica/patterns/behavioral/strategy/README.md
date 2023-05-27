# Strategy 

In the strategy pattern, the base strategy (interface or abstract class) is used as a field type in an object.  Then, 
whenever that type of behavior is needed, the strategy (or a default) is called upon to do that work.  The strategy 
itself is then responsible for any internal logic / behavior to achieve the desired outcome.  

This example is a bit contrived, but the logic is that only adult animals can fly, if at all.  As the animal grows, it may learn how to do so.  However, in reality,
older baby birds may learn how to fly before they are able to leave the nest and fully support themselves.

In a software environment, this approach might be useful to perform a slow-roll-out of a new feature, or A-B testing, etc.

```mermaid
---
title: Strategy Pattern Class Diagram
---
classDiagram
    Animal ..> FlyingStrategy
    AirplaneStrategy --|>  FlyingStrategy
    FlapWingsStrategy --|> FlyingStrategy
    GlidingStrategy --|> FlyingStrategy
    UnableToFlyStrategy --|> FlyingStrategy
    class FlyingStrategy {
        +FlyingStrategy getFlyingStrategy()*
    }
    class AirplaneStrategy {
        +FlyingStrategy getFlyingStrategy()
    }
    class FlapWingsStrategy {
        +FlyingStrategy getFlyingStrategy()
    }
    class GlidingStrategy {
        +FlyingStrategy getFlyingStrategy()
    }
    class UnableToFlyStrategy {
        +FlyingStrategy getFlyingStrategy()
    }
    class Animal {
        -ScientificClassification scientificClassification
        -String name
        -String description
        -FlyingStrategy flyingStrategy
        +setFlyingStrategy(FlyingStrategy)
        +ScientificClassification getScientificClassification()
        +String getName()
        +String getDescription()
        +FlyingStrategy getFlyingStrategy()
        +int hashCode()
        +boolean equals()
        +String toString()
    }

```