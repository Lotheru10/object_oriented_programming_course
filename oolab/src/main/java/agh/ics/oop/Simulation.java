package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> startingPositions, List<MoveDirection> moves) {
        this.animals = new ArrayList<>();
        for (Vector2d position : startingPositions) {
            animals.add(new Animal(position));
        }
        this.moves = moves;
    }
    public void run() {
        int animalCount = animals.size();
        for (int i = 0; i < moves.size(); i++) {
            Animal currentAnimal = animals.get(i % animalCount);
            currentAnimal.move(moves.get(i));
            System.out.printf("%d : %s%n", i % animalCount, currentAnimal.toString());
        }
    }
    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }
}
