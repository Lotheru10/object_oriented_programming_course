package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{
    protected Vector2d lowerLeft = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    protected Vector2d upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected final Map<Vector2d, Animal> animals= new HashMap<>();
    protected final MapVisualizer mapVisualizer = new MapVisualizer(this);

    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position) ) {
            animals.put(position, animal);
            return true;
        }
        return false;
    }

    public void move(Animal animal, MoveDirection direction) {
        Vector2d position = animal.getPosition();

        if (animals.containsKey(position)) {
            animal.move(direction, this);
            if (!position.equals(animal.getPosition())) {
                animals.remove(position);
                animals.put(animal.getPosition(), animal);
            }
        }

    }
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) && position.follows(lowerLeft) && position.precedes(upperRight);
    }

    public List<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>(animals.values());
        return List.copyOf(Collections.unmodifiableList(elements));
    }
}
