package com.webcheckers.model;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SWEN-261
 * Color.java
 * Team: \v
 * Purpose: Used to test Position
 */
@Tag("Model-tier")
public class PositionTest {


    @Test
    public void testHashCode(){
        Position p = new Position(2,6);
        assertEquals(p.hashCode(), 2*31+6);
    }

    @Test
    public void testGetRow(){
        Position p = new Position(2,6);
        assertEquals(p.getRow(),2);
    }

    @Test
    public void testGetCell(){
        Position p = new Position(2,6);
        assertEquals(p.getCell(),6);
    }

    @Test
    public void testEquals(){
        Position p = new Position(2,6);
        Position p2 = new Position(2,6);
        Position p3 = new Position(2,1);
        Position p4 = new Position(1,6);
        Position p5 = null;

        assertEquals(p,p2);
        assertNotEquals(p,p3);
        assertNotEquals(p,p4);
        assertNotEquals(p,p5);
    }

    @Test
    public void testToString(){
        Position p = new Position(2,6);
        assertEquals(p.toString(),"Position{" + "row=" + '2' + ", cell=" + '6' + '}');
    }
}
