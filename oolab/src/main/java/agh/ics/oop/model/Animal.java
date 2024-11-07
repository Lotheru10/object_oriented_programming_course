package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }
    public Animal(Vector2d initialPosition) {
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
    }

    public Vector2d getposition() {
        return this.position;
    }

    @Override
    public String toString() {
        return "Animal location: orientation=%s, position=%s}".formatted(orientation, position);
    }
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }
    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector());
                if (newPosition.follows(new Vector2d(0, 0)) && newPosition.precedes(new Vector2d(4, 4))) {
                    position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = position.subtract(orientation.toUnitVector());
                if (newPosition.follows(new Vector2d(0, 0)) && newPosition.precedes(new Vector2d(4, 4))) {
                    position = newPosition;
                }
            }
        }
    }
    public Vector2d getPosition() {
        return position;
    }
    public MapDirection getOrientation() {
        return orientation;
    }
}
