package agh.ics.oop.exceptions;

import agh.ics.oop.model.Vector2d;

public class IncorrectPositionException extends Exception {
    public IncorrectPositionException(Vector2d position) {
        super(String.format("Position (%d, %d) is not correct", position.getX(), position.getY()));
    }
}

