package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    Map<Vector2d, Animal> animals= new HashMap<>();

    private final int width;
    private final int height;
    private final static Vector2d bottom = new Vector2d(0,0);
    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (!isOccupied(position) && canMoveTo(position) ) {
            animals.put(position, animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d position = animal.getPosition();
        MapDirection orientation = animal.getOrientation();
        Vector2d newPosition = position;

        switch(direction) {
            case LEFT -> animal.setOrientation(orientation.previous());
            case RIGHT -> animal.setOrientation(orientation.next());
            case FORWARD -> newPosition = position.add(orientation.toUnitVector());
            case BACKWARD -> newPosition = position.subtract(orientation.toUnitVector());
        }
        if (canMoveTo(newPosition)) {
            if (direction == MoveDirection.FORWARD || direction == MoveDirection.BACKWARD) {
                animals.put(newPosition, animal);
                animals.remove(position);
                animal.setPosition(newPosition);

            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return isOccupied(position) && position.follows(bottom) && position.precedes(new Vector2d(width-1, height-1));
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }

}
