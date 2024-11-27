package agh.ics.oop;
import agh.ics.oop.exceptions.IncorrectPositionException;
import agh.ics.oop.model.*;

import java.util.List;
import static agh.ics.oop.OptionsParser.parse;


public class World {
    public static void main(String[] args) throws IncorrectPositionException {
        System.out.println("system wystartowal");
        List<MoveDirection> directions = parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        AbstractWorldMap map = new GrassField(10);
        map.addObserver(new ConsoleMapDisplay());
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
        System.out.println("system zakonczyl dzialanie");
    }
}