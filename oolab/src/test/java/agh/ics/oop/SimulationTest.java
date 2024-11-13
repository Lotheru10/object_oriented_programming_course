package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

    class SimulationTest {
        List<Animal> animals;
        @Test
        void animalOrientationAfterMoves() {
            List<Vector2d> startingPositions = List.of(new Vector2d(2, 2));
            List<MoveDirection> moves = List.of(MoveDirection.RIGHT, MoveDirection.RIGHT);
            Simulation simulation = new Simulation(startingPositions, moves);

            simulation.run();

            Animal animal = simulation.getAnimals().getFirst();
            assertEquals(MapDirection.SOUTH, animal.getOrientation());
        }

        @Test
        void animalMovementToLocation() {
            List<Vector2d> startingPositions = new ArrayList<>();
            startingPositions.add(new Vector2d(2, 2));
            List<MoveDirection> moves = List.of(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD);
            Simulation simulation = new Simulation(startingPositions, moves);

            simulation.run();

            Animal animal = simulation.getAnimals().getFirst();
            assertEquals(new Vector2d(1, 4), animal.getPosition());
        }

        @Test
        void animalStaysInMap() {
            List<Vector2d> startingPositions = List.of(new Vector2d(0, 3));
            List<MoveDirection> moves = List.of(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD);
            Simulation simulation = new Simulation(startingPositions, moves);

            simulation.run();

            Animal animal = simulation.getAnimals().getFirst();
            assertTrue(animal.getPosition().getX() !=5 && animal.getPosition().getX() !=-5
                            && animal.getPosition().getY() !=5 && animal.getPosition().getY() !=-5);
        }

        @Test
        void optionsParserWorks() {
            String[] input = {"f", "b", "r", "l", "aaaa"};
            List<MoveDirection> directions = OptionsParser.parse(input);

            assertEquals(4, directions.size());
            assertEquals(MoveDirection.FORWARD, directions.get(0));
            assertEquals(MoveDirection.BACKWARD, directions.get(1));
            assertEquals(MoveDirection.RIGHT, directions.get(2));
            assertEquals(MoveDirection.LEFT, directions.get(2));
        }
    }