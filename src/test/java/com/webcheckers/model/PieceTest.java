package com.webcheckers.model;

import com.webcheckers.util.Color;
import com.webcheckers.util.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test for the Piece component.
 *
 * @author Anthony DiCarlo
 */
public class PieceTest {

    /**
     * Test that ensures that red pieces can be created
     */
    @Test
    public void colorRedTest(){
        final Piece Cut = new Piece(Color.RED, Type.SINGLE);
        assertEquals(Color.RED, Cut.getColor());
    }

    /**
     * Test that ensures the white pieces can be created
     */
    @Test
    public void colorWhiteTest(){
        final Piece Cut = new Piece(Color.WHITE, Type.SINGLE);
        assertEquals(Color.WHITE, Cut.getColor());
    }

    /**
     * Test that ensures that single pieces can be created
     */
    @Test
    public void typeSingleTest(){
        final Piece Cut = new Piece(Color.WHITE, Type.SINGLE);
        assertEquals(Type.SINGLE, Cut.getType());
    }

    /**
     * Test that ensures the king pieces can be created
     */
    @Test
    public void typeKingTest(){
        final Piece Cut = new Piece(Color.WHITE, Type.KING);
        assertEquals(Type.KING, Cut.getType());
    }

    /**
     * Test the equals method
     */
    @Test
    public void equalsTest(){
        Piece cut = new Piece(Color.WHITE, Type.SINGLE);
        Piece red = new Piece(Color.RED, Type.SINGLE);
        Piece king = new Piece(Color.WHITE, Type.KING);
        Piece nul = null;
        Space sp = new Space(69, 47);
        assertFalse(cut.equals(sp));
        assertTrue(cut.equals(cut));
        assertFalse(cut.equals(red));
        assertFalse(cut.equals(king));
        assertNotEquals(cut,nul);
    }


    @Test
    public void testPromoteToKing(){
        Piece p = new Piece(Color.RED,Type.SINGLE);
        p.promoteToKing();
        assertEquals(p.getType(),Type.KING);
    }


    @Test
    public void testCopyPiece(){
        Piece p = new Piece(Color.RED,Type.SINGLE);
        Piece copy = p.copyPiece();
        assertEquals(p,copy);
    }


}
