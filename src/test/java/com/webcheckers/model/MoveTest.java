package com.webcheckers.model;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for Move class belonging to Model tier
 */
@Tag("Model-tier")
public class MoveTest {

    /**
     * validate that Move() creates a move
     */
    @Test
    public void testMove(){
        Move move = new Move(new Position(1,1), new Position(2,2));
        assertNotNull(move, "Move not created properly");
    }

    /**
     * validate that getStart() works properly
     */
    @Test
    public void testGetStart(){
        Move move = new Move(new Position(1,1), new Position(2,2));
        assertEquals(move.getStart(), new Position(1,1), "Start not correct");
    }

    /**
     * validate that getEnd() works properly
     */
    @Test
    public void testGetEnd(){
        Move move = new Move(new Position(1,1), new Position(2,2));
        assertEquals(move.getEnd(), new Position(2,2), "End not correct");
    }

    /**
     * validate that toString() works properly
     */
    @Test
    public void testToString(){
        Move move = new Move(new Position(1,1), new Position(2,2));
        String expStr = "Move{start=Position{row=1, cell=1}, end=Position{row=2, cell=2}}";
        assertEquals(expStr, move.toString(), "Move toString not correct");
    }

    /**
     * validate that equals() works properly
     */
    @Test
    public void testEquals(){
        Move move = new Move(new Position(1,1), new Position(2,2));
        Move move1 = new Move(new Position(1,1), new Position(2,2));
        Move move2 = new Move(new Position(2,2), new Position(3,3));
        Move move3 = null;
        Move move4 = new Move(new Position(1,1), new Position(3,3));
        Move move5 = new Move(new Position(2,2), new Position(2,2));

        String str = "not a Move";
        assertNotEquals(move,str, "Move equals messed up");
        assertEquals(move,move1, "Move equals messed up");
        assertNotEquals(move,move2, "Move equals messed up");
        assertNotEquals(move,move3);
        assertNotEquals(move,move4);
        assertNotEquals(move,move5
        );
    }

    /**
     * test the hashCode() method
     */
    @Test
    public void testHash(){
        Move move = new Move(new Position(1,1), new Position(2,2));
        assertEquals(move.hashCode(), 1056);
    }
}
