package com.webcheckers.model;


import com.webcheckers.util.Color;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit test for the Row component
 *
 * @author Joseph Rumfelt
 */
@Tag("Model-tier")
public class RowTest {

    private Row row;


    /**
     *Test that the constructor works with color Red
     */
    @Test
    public void ctorRedTest(){
        Board board = new Board();
        row = new Row(4, board, Color.RED);
    }

    /**
     *Test that the constructor works with color White
     */
    @Test
    public void ctorWhiteTest(){
        Board board = new Board();
        row = new Row(0, board, Color.WHITE);
    }

    /**
     * Tests that getIndex works
     */
    @Test
    public void getIndexTest() {
        Board board = new Board();
        row = new Row(4, board, Color.RED);
        int teste = row.getIndex();
        int index = 4;
        assertEquals(teste, index);
    }



}
