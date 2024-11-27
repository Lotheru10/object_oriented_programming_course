package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    @Test
    public void testPlace(){
        WorldMap map = new RectangularMap(5,5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal();
        assertTrue(map.place(animal1));
        assertFalse(map.place(animal2));
    }
    @Test
    public void testMove(){
        WorldMap map = new RectangularMap(5,5);
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
    public void testIfMapWorks(){
        WorldMap map = new RectangularMap(5,5);
        Animal animal = new Animal();
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }

    @Test
    public void testCanMoveTo(){
        WorldMap map = new RectangularMap(5,5);
        Animal animal1 = new Animal();
        map.place(animal1);
        assertTrue(map.canMoveTo(new Vector2d(2, 3)));
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
    }
    @Test
    public void testIsOccupied(){
        WorldMap map = new RectangularMap(5,5);
        Animal animal1 = new Animal();
        map.place(animal1);
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }
    @Test
    public void testObjectAt(){
        WorldMap map = new RectangularMap(6,5);
        Animal animal1 = new Animal();
        map.place(animal1);
        assertEquals(animal1, map.objectAt(new Vector2d(2, 2)));
    }
    @Test
    public void testGetElements(){
        WorldMap map = new RectangularMap(6,5);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(3, 3));
        map.place(animal1);
        map.place(animal2);
        assertEquals(2, map.getElements().size());
    }




}