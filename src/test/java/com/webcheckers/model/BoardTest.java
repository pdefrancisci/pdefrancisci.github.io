package com.webcheckers.model;

import com.webcheckers.util.Color;
import com.webcheckers.util.Type;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Board.java, in the model tier
 */
@Tag("Model-tier")
public class BoardTest {

    /**
     * verify that the constructor works properly
     */
    @Test
    public void testBoard(){
        Board board = new Board();
        assertNotNull(board);
    }

    /**
     * make sure getBoard() returns the board correctly
     */
    @Test
    public void getBoardTest(){
        Board board = new Board();
        Space space = new Space(0,1);
        space.setPiece(new Piece(Color.WHITE, Type.SINGLE));
        assertEquals(board.getBoard()[0][1], space);
    }

    /**
     * test getItem() method
     */
    @Test
    public void getItemTest(){
        Board board = new Board();
        Space space = new Space(0, 0);
        assertEquals(space, board.getItem(0,0));
        assertNull(board.getItem(69, 420));
    }

    /**
     * test copy() method
     */
    @Test
    public void copyTest(){
        Board board = new Board();
        board.getBoard()[0][1].setPiece(new Piece(Color.WHITE, Type.SINGLE));
        Board board2 = board.copy();
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                assertEquals(board.getBoard()[i][j], board2.getBoard()[i][j]);
            }
        }
    }
}
