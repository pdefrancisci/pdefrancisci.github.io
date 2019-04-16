package com.webcheckers.model;

import com.webcheckers.util.Color;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the player object belonging to the model tier
 */
@Tag("Model-tier")
public class PlayerTest {

    /**
     * test to make sure getName() method works properly
     */
    @Test
    public void testGetName(){
        Player p = new Player("TESTNAME");
        assertEquals(p.getName(),"TESTNAME","Player name is not set correctly");
    }

    /**
     * test to make sure game and color are set properly when player joins a game
     */
    @Test
    public void testJoinGame(){
        Player p = new Player("TESTNAME");
        p.joinGame(Color.RED,null);
        assertNull(p.getCheckerGame(),"Checker game is not set correctly");
        assertEquals(p.getColor(),Color.RED, "Color is not set correctly");
    }

    /**
     * test to ensure player is not in a game when they are created
     */
    @Test
    public void testIsInGame(){
        Player p = new Player("TESTNAME");
        assertFalse(p.isInGame(),"Player should not be in game");
    }

    /**
     * test to ensure checkerGame is nonexistant when player is created
     */
    @Test
    public void testGetCheckerGame(){
        Player p = new Player("TESTNAME");
        assertNull(p.getCheckerGame(),"Checker game not set correctly");
    }

    /**
     * test to ensure player is not assigned a color upon creation
     */
    @Test
    public void testGetColor(){
        Player p = new Player("TESTNAME");
        assertEquals(p.getColor(),Color.NONE,"Player should not have a color yet");
    }

    /**
     * test to ensure equals() method functions properly
     */
    @Test
    public void testEquals(){
        Player p = new Player("TESTNAME");
        Player p1 = new Player("TESTNAME");
        Player p2 = new Player("BadName");
        Player p3 = null;
        Integer p4 = 1;

        assertEquals(p,p1,"Equals does not work, these should be equal");
        assertNotEquals(p,p2,"Equals does not work, these should not be equal");
        assertNotNull(p);
        assertNotEquals(p,p3);
        assertNotEquals(p,p4);
    }

    /**
     * test to ensure hashCode() method works properly
     */
    @Test
    public void testHashCode(){
        Player p = new Player("TESTNAME");
        assertEquals(p.hashCode(),p.getName().hashCode(),"Hashcode isn't correct");
    }

    /**
     * test to ensure setHasError() method works properly
     */
    @Test
    public void testSetHasError(){
        Player p = new Player("TESTNAME");
        p.setHasError(true);
        assertTrue(p.isHasError(),"Setting error doesn't work");
        p.setHasError(false);
        assertFalse(p.isHasError(),"Setting error doesn't work");
    }

    /**
     * test to ensure player has no errors upon creation
     */
    @Test
    public void testIsHasError(){
        Player p = new Player("TESTNAME");
        assertFalse(p.isHasError(),"The game should not have an error");
    }

    /**
     * test the setInGame() method
     */
    @Test
    public void setInGameTest(){
        Player jeff = new Player("Jeff");
        assertFalse(jeff.isInGame());
        jeff.setInGame(true);
        assertTrue(jeff.isInGame());
    }

    /**
     * test the toString() method
     */
    @Test
    public void toStringTest(){
        Player jeff = new Player("Jeff");
        final String tojeff = "Player{name='Jeff', inGame=false, color=NONE}";
        assertEquals(jeff.toString(), tojeff);
    }

}