package agh.ics.oop;
import agh.ics.oop.model.exceptions.IncorrectPositionException;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.SimulationEngine;

import java.util.ArrayList;
import java.util.List;
import static agh.ics.oop.OptionsParser.parse;


public class World {
    public static void main(String[] args) throws IncorrectPositionException {
        try {
        System.out.println("system wystartowal");
        List<MoveDirection> directions = parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
//        AbstractWorldMap map1 = new GrassField(10);
//        AbstractWorldMap map2 = new RectangularMap(5, 5, new Boundary(new Vector2d(0,0), new Vector2d(5,5)));
//        map1.addObserver(new ConsoleMapDisplay());
//        map2.addObserver(new ConsoleMapDisplay());
//        Simulation simulation1 = new Simulation(positions, directions, map1);
//        Simulation simulation2 = new Simulation(positions, directions, map2);
//        SimulationEngine engine = new SimulationEngine(List.of(simulation1, simulation2));
        List<Simulation> simulations = new ArrayList<>();
        for (int i=0;i<1000;i++){
            AbstractWorldMap map = new GrassField(10);
            map.addObserver(new ConsoleMapDisplay());
            Simulation simulation = new Simulation(positions, directions, map);
            simulations.add(simulation);
        }
        SimulationEngine engine = new SimulationEngine(simulations);
        engine.runAsyncInThreadPool();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("system zakonczyl dzialanie");
    }
}