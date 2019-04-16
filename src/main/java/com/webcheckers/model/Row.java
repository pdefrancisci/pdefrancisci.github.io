package com.webcheckers.model;

import com.webcheckers.util.Color;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * SWEN-261
 * Row.java
 * Team: \v
 * Purpose: This represents a row of the board
 */
public class Row implements Iterable<Space>{

    List<Space> spaces;
    private int index;

    /**
     * Constructor for row
     * @param rowIndex Index of the row
     * @param board board the row belongs to
     * @param color color of the player
     */
    public Row(int rowIndex, Board board, Color color){
        spaces = new LinkedList<>();
        this.index = rowIndex;
        if(color == Color.RED){
            for(int i = 0; i < 8; i++){
                spaces.add(board.getBoard()[rowIndex][i]);
            }
        } else {
            for(int i = 7; i > -1; i--){
                spaces.add(board.getBoard()[rowIndex][i]);
            }
        }
    }

    /**
     * Getter for the row index
     * @return The row index
     */
    public int getIndex(){
        return index;
    }

    /**
     * Gets the iterator for the row
     * @return iterator for the row
     */
    @Override
    public Iterator<Space> iterator() {
        return spaces.iterator();
    }
}
