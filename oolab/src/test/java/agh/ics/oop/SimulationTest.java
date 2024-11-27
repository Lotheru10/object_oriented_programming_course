package agh.ics.oop;

import agh.ics.oop.exceptions.IncorrectPositionException;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationTest {

    WorldMap map = new RectangularMap(5, 5);

    @Test
    void animalOrientationAfterMoves() throws IncorrectPositionException {
        List<Vector2d> startingPositions = List.of(new Vector2d(2, 2));
        List<MoveDirection> moves = List.of(MoveDirection.RIGHT, MoveDirection.RIGHT);

        Simulation simulation = new Simulation(startingPositions, moves, map);

        simulation.run();

        Animal animal = simulation.getAnimals().get(0);
        assertEquals(MapDirection.SOUTH, animal.getOrientation());
    }

    @Test
    void animalMovementToLocation() throws IncorrectPositionException {
        List<Vector2d> startingPositions = new ArrayList<>();
        startingPositions.add(new Vector2d(2, 2));
        List<MoveDirection> moves = List.of(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD);

        Simulation simulation = new Simulation(startingPositions, moves, map);

        simulation.run();

        Animal animal = simulation.getAnimals().get(0);
        assertEquals(new Vector2d(1, 4), animal.getPosition());
    }

    @Test
    void animalStaysInMap() throws IncorrectPositionException {
        List<Vector2d> startingPositions = List.of(new Vector2d(0, 3));
        List<MoveDirection> moves = List.of(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD);

        Simulation simulation = new Simulation(startingPositions, moves, map);

        simulation.run();

        Animal animal = simulation.getAnimals().getFirst();
        assertTrue(animal.getPosition().getX() >= 0 && animal.getPosition().getX() <= 4
                && animal.getPosition().getY() >= 0 && animal.getPosition().getY() <= 4);
    }

    @Test
    void optionsParserWorks() {
        String[] input = {"f", "b", "r", "l", "aaaa"};
        List<MoveDirection> directions = OptionsParser.parse(input);

        assertEquals(4, directions.size());
        assertEquals(MoveDirection.FORWARD, directions.get(0));
        assertEquals(MoveDirection.BACKWARD, directions.get(1));
        assertEquals(MoveDirection.RIGHT, directions.get(2));
        assertEquals(MoveDirection.LEFT, directions.get(3));
    }
}
