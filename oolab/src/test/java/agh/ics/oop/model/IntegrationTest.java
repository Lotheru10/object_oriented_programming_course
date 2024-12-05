package agh.ics.oop.model;

import agh.ics.oop.Simulation;
import agh.ics.oop.exceptions.IncorrectPositionException;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {
    private WorldMap map;

    @Test
    void testAnimalsPlacementOnMap() throws IncorrectPositionException {
        List<Vector2d> startingPositions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));
        List<MoveDirection> moves = List.of(MoveDirection.FORWARD);
        map = new RectangularMap(5, 5);

        Simulation simulation = new Simulation(startingPositions, moves, map);
        List<Animal> animals = simulation.getAnimals();
        assertEquals(2, animals.size());
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(3, 3)));
    }

    @Test
    void testAnimalMovement() throws IncorrectPositionException {
        List<Vector2d> startingPositions = List.of(new Vector2d(2, 2));
        List<MoveDirection> moves = List.of(
                MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.LEFT
        );
        map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(startingPositions, moves, map);
        simulation.run();
        Animal animal = simulation.getAnimals().get(0);
        assertEquals(new Vector2d(3, 3), animal.getPosition());
        assertEquals("^", animal.toString());
    }

    @Test
    void testAnimalMovementBounds() throws IncorrectPositionException {
        List<Vector2d> startingPositions = List.of(new Vector2d(0, 0));
        List<MoveDirection> moves = List.of(MoveDirection.BACKWARD,
                MoveDirection.LEFT, MoveDirection.FORWARD
        );
        map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(startingPositions, moves, map);
        simulation.run();

        Animal animal = simulation.getAnimals().get(0);
        assertEquals(new Vector2d(0, 0), animal.getPosition());
    }

    @Test
    void testMultipleAnimalsMovement() throws IncorrectPositionException {
        List<Vector2d> startingPositions = List.of(new Vector2d(2, 2), new Vector2d(3, 3));
        List<MoveDirection> moves = List.of(
                MoveDirection.FORWARD, MoveDirection.LEFT,
                MoveDirection.RIGHT, MoveDirection.FORWARD
        );
        map = new RectangularMap(5, 5);

        Simulation simulation = new Simulation(startingPositions, moves, map);
        simulation.run();
        Animal firstAnimal = simulation.getAnimals().get(0);
        Animal secondAnimal = simulation.getAnimals().get(1);

        assertEquals(new Vector2d(2, 3), firstAnimal.getPosition());
        assertEquals(">", firstAnimal.toString());

        assertEquals(new Vector2d(3, 3), secondAnimal.getPosition());
        assertEquals("<", secondAnimal.toString());
    }
}
