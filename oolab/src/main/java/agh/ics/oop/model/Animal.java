package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal() {
        this(new Vector2d(2,2));
    }
    public Animal(Vector2d initialPosition) {
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
    }

    @Override
    public String toString() {
        return "Animal location: orientation=%s, position=%s}".formatted(orientation, position);
    }
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }
    public void move(MoveDirection direction) {
        Vector2d moveVector = switch (direction) {
            case FORWARD -> orientation.toUnitVector();
            case BACKWARD -> orientation.toUnitVector().opposite();
            default -> null;
        };
        if (moveVector != null) {
            Vector2d potentialPosition = position.add(moveVector);
            if (potentialPosition.follows(new Vector2d(0, 0)) && potentialPosition.precedes(new Vector2d(4, 4))) {
                position = potentialPosition;
            }
        } else {
            switch (direction) {
                case RIGHT -> orientation = orientation.next();
                case LEFT -> orientation = orientation.previous();
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
