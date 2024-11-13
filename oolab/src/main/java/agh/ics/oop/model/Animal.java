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
        return switch (orientation) {
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };
    }
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }
    public void move(MoveDirection direction, MoveValidator validator) {
        Vector2d moveVector = switch (direction) {
            case FORWARD -> orientation.toUnitVector();
            case BACKWARD -> orientation.toUnitVector().opposite();
            default -> null;
        };
        if (moveVector != null) {
            Vector2d potentialPosition = position.add(moveVector);
            if (validator.canMoveTo(potentialPosition)) {
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
    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }
    public void setOrientation(MapDirection orientation) {
        this.orientation = orientation;
    }

}
