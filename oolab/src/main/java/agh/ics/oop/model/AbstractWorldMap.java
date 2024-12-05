package agh.ics.oop.model;

import agh.ics.oop.exceptions.IncorrectPositionException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap{
    protected final Map<Vector2d, Animal> animals= new HashMap<>();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);
    private final List<MapChangeListener> observers = new ArrayList<>();

    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }
    public void place(Animal animal) throws IncorrectPositionException {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position) ) {
            animals.put(position, animal);
            notifyObservers("placed");
        }
        else throw new IncorrectPositionException(position);

    }

    public void move(Animal animal, MoveDirection direction) {
        Vector2d position = animal.getPosition();
        if (animals.containsKey(position)) {
            animal.move(direction, this);
            if (!position.equals(animal.getPosition())) {
                animals.remove(position);
                animals.put(animal.getPosition(), animal);
                notifyObservers("moved");
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
        return !isOccupied(position);
    }

    public List<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>(animals.values());
        return elements;
    }

    public abstract Boundary getCurrentBounds();

    @Override
    public String toString() {
        Boundary currentBounds = getCurrentBounds();
        return mapVisualizer.draw(currentBounds.lowerLeft(), currentBounds.upperRight());
    }
}

