package agh.ics.oop.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void add() {
        Vector2d v1 = new Vector2d(1,1);
        Vector2d v2 = new Vector2d(0,0);
        Vector2d v3 = new Vector2d(5,4);
        Vector2d v4 = new Vector2d(3,1);

        assertEquals(new Vector2d(1,1), v1.add(v2));
        assertEquals(new Vector2d(8,5), v3.add(v4));
    }
    @Test
    public void subtract() {
        Vector2d v1 = new Vector2d(1,1);
        Vector2d v2 = new Vector2d(0,0);
        Vector2d v3 = new Vector2d(5,4);
        Vector2d v4 = new Vector2d(3,1);

        assertEquals(new Vector2d(1,1), v1.subtract(v2));
        assertEquals(new Vector2d(2,3), v3.subtract(v4));
    }
    @Test
    public void opposite(){
        Vector2d v1 = new Vector2d(5,3);

        assertEquals(new Vector2d(-5,-3), v1.opposite());
    }
    @Test
    public void testToString(){
        Vector2d v1 = new Vector2d(5,3);

        assertEquals("(5,3)", v1.toString());
    }

    @Test
    public void precedes(){
        Vector2d v1 = new Vector2d(0,0);
        Vector2d v2 = new Vector2d(1,1);
        Vector2d v3 = new Vector2d(2,5);
        Vector2d v4 = new Vector2d(0,0);

        assertTrue(v1.precedes(v2));
        assertFalse(v3.precedes(v4));
    }

    @Test
    public void follows(){
        Vector2d v1 = new Vector2d(0,0);
        Vector2d v2 = new Vector2d(1,1);
        Vector2d v3 = new Vector2d(2,5);
        Vector2d v4 = new Vector2d(2,4);
        Vector2d v5 = new Vector2d(0,0);

        assertFalse(v1.follows(v2));
        assertTrue(v3.follows(v4));
        assertTrue(v5.follows(v5));
    }
    @Test
    public void testEquals() {
        Vector2d v1 = new Vector2d(3, 7);
        Vector2d v2 = new Vector2d(3, 7);
        Vector2d v3 = new Vector2d(3, 7);
        Vector2d v4 = new Vector2d(3, 7);
        Vector2d v5 = new Vector2d(5, 8);

        assertTrue(v1.equals(v1));
        assertTrue(v2.equals(v3));
        assertFalse(v4.equals(v5));
    }

    @Test
    public void testUpperRight() {
        // Arrange
        Vector2d v1 = new Vector2d(3, 7);
        Vector2d v2 = new Vector2d(5, 4);
        Vector2d v3 = new Vector2d(-2, 10);

        assertEquals(new Vector2d(5, 7), v1.upperRight(v2));
        assertEquals(new Vector2d(5, 10), v2.upperRight(v3));
        assertEquals(new Vector2d(3, 10), v3.upperRight(v1));
    }

    @Test
    public void testLowerLeft() {
        Vector2d v1 = new Vector2d(3, 7);
        Vector2d v2 = new Vector2d(5, 4);
        Vector2d v3 = new Vector2d(-2, 10);

        assertEquals(new Vector2d(3, 4), v1.lowerLeft(v2));
        assertEquals(new Vector2d(-2, 4), v2.lowerLeft(v3));
        assertEquals(new Vector2d(-2, 7), v3.lowerLeft(v1));
    }
}