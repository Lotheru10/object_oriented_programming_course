package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;


import java.util.Collections;
import java.util.ArrayList;
import java.util.List;


public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> moves;
    private final WorldMap map;

    public Simulation(List<Vector2d> startingPositions, List<MoveDirection> moves, WorldMap map) {
        if (startingPositions == null || startingPositions.isEmpty()) {
            throw new IllegalArgumentException("Starting positions cannot be empty");
        }
        this.animals = new ArrayList<>();
        for (Vector2d position : startingPositions) {
            Animal animal = new Animal(position);
            if (map.place(animal)) {
                animals.add(animal);
            }
        }
        this.moves = moves;
        this.map = map;
    }
    public void run() {
        if (animals.isEmpty()) {
            throw new IllegalStateException("There are no animals in the map");
        }
        int animalCount = animals.size();

        for (int i = 0; i < moves.size(); i++) {
            Animal currentAnimal = animals.get(i % animalCount);
            MoveDirection direction = moves.get(i);
            map.move(currentAnimal, direction);
            System.out.println(map);
        }
    }
    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }
    public List<MoveDirection> getDirections() {
        return moves;
    }
}
