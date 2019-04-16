package com.webcheckers.model;

import com.webcheckers.util.Color;
import com.webcheckers.util.Type;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for Space class belonging to the model tier
 *
 * @author Josh Kraines
 */
@Tag("Model-tier")
public class SpaceTest {

    /**
     * verify that the getCol() method works
     */
    @Test
    public void getColTest(){
        Space teste = new Space(47, 11);
        assertEquals(11, teste.getCol());
    }

    /**
     * verify that the getRow() method works
     */
    @Test
    public void getRowTest(){
        Space teste = new Space(47, 11);
        assertEquals(47, teste.getRow());
    }

    /**
     * verify that setPiece() works properly
     */
    @Test
    public void setPieceTest(){
        Space teste = new Space(47, 11);
        Piece piece = new Piece(Color.RED, Type.SINGLE);
        teste.setPiece(piece);
        assertEquals(piece, teste.getPiece());
    }

    /**
     * verify hasPiece() works properly
     */
    @Test
    public void hasPieceTest(){
        Space teste = new Space(47, 11);
        assertFalse(teste.hasPiece());
        teste.setPiece(new Piece(Color.RED, Type.SINGLE));
        assertTrue(teste.hasPiece());
    }

    /**
     * verify that getPiece() works properly
     */
    @Test
    public void getPieceTest(){
        Space teste = new Space(47, 11);
        Piece piece = new Piece(Color.RED, Type.SINGLE);
        teste.setPiece(piece);
        assertEquals(piece, teste.getPiece());
    }

    /**
     * verify that setBlack() sets the color of the space to black
     */
    @Test
    public void setBlackTest(){
        Space teste = new Space(47, 11);
        teste.setBlack();
        assertTrue(teste.isValid());
    }

    /**
     * verify that isValid() returns the proper result
     */
    @Test
    public void isValidTest(){
        Space teste = new Space(47, 11);
        assertFalse(teste.isValid());
        teste.setBlack();
        assertTrue(teste.isValid());
        teste.setPiece(new Piece(Color.WHITE, Type.SINGLE ));
        assertFalse(teste.isValid());
    }

    /**
     * verify that getCellIdx() returns the column of the Space
     */
    @Test
    public void getCellIdxTest(){
        Space teste = new Space(47, 11);
        assertEquals(11, teste.getCellIdx());
    }

    /**
     * verify that equals() functions properly
     */
    @Test
    public void equalsTest(){
        Space teste = new Space(47, 11);
        Space teste2 = new Space(47, 11);
        Space teste3 = new Space(47, 10);
        Space teste4 = new Space(11, 47);
        assertEquals(true, teste.equals(teste2));
        assertEquals(false, teste.equals(null));
        assertEquals(false, teste.equals(teste3));
        assertFalse(teste.equals(teste4));
    }

    /**
     * verify toString() returns proper string representation of the Space
     */
    @Test
    public void toStringTest(){
        Space teste = new Space(47, 11);
        String none = "NONE";
        String white = "WHITE";
        String red = "RED";
        assertEquals(none, teste.toString());
        teste.setPiece(new Piece(Color.RED, Type.SINGLE));
        assertEquals(red, teste.toString());
        teste.setPiece(new Piece(Color.WHITE, Type.SINGLE));
        assertEquals(white, teste.toString());
    }
}
