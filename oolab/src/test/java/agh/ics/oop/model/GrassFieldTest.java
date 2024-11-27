package agh.ics.oop.model;

import agh.ics.oop.exceptions.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    @Test
    void checkNumberOfGrasses() {
        GrassField grassField = new GrassField(10);
        int generated = grassField.getElements().size();
        assertEquals(10, generated);
    }
    @Test
    void grassesInProperRange(){
        GrassField grassField = new GrassField(8);
        List<WorldElement> grasses = grassField.getElements();
        boolean proper = true;
        for (WorldElement worldElement : grasses) {
            if(worldElement.getPosition().getX()>Math.sqrt(80) || worldElement.getPosition().getY()>Math.sqrt(80)){
                proper = false;
                break;
            }
        }
        assertTrue(proper);
    }
    @Test
    public void testPlace() throws IncorrectPositionException {
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal();
        map.place(animal1);
        IncorrectPositionException exception = assertThrows(IncorrectPositionException.class, () -> map.place(animal2));
        assertEquals("Position (2, 2) is not correct", exception.getMessage());
    }
    @Test
    public void testMove() throws IncorrectPositionException {
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(3, 3));
        map.place(animal1);
        map.place(animal2);
        map.move(animal1, MoveDirection.FORWARD);
        map.move(animal2, MoveDirection.RIGHT);
        map.move(animal2, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal1.getPosition());
        assertEquals(new Vector2d(4, 3), animal2.getPosition());
    }
    @Test
    public void testIfMapWorks() throws IncorrectPositionException {
        WorldMap map = new GrassField(10);
        Animal animal = new Animal();
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }

    @Test
    public void testCanMoveTo() throws IncorrectPositionException {
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        map.place(animal1);
        assertTrue(map.canMoveTo(new Vector2d(2, 3)));
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
    }
    @Test
    public void testIsOccupied() throws IncorrectPositionException {
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal();
        map.place(animal1);
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }
    @Test
    public void testObjectAt() throws IncorrectPositionException {
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        map.place(animal1);
        assertEquals(animal1, map.objectAt(new Vector2d(2, 2)));
    }
    @Test
    public void testGetElements() throws IncorrectPositionException {
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(2, 3));
        map.place(animal1);
        map.place(animal2);
        assertEquals(12, map.getElements().size());
    }

}