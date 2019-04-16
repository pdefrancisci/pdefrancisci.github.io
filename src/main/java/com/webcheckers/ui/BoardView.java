package com.webcheckers.ui;

import com.webcheckers.model.Board;
import com.webcheckers.util.Color;
import com.webcheckers.model.Row;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * SWEN-261
 * BoardView.java
 * Team: \v
 * Purpose: Creates an iterable List of the board used to correctly display the board for each user
 */
public class BoardView implements Iterable<Row> {

    private List<Row> rows;

    /**
     * Constructor for the BoardView
     * @param board The board used in the game
     * @param color The color of the player
     */
    public BoardView(Board board, Color color){
        rows = new LinkedList<>();

        if(color == Color.RED){
            for(int i = 0; i < 8; i++){
                rows.add(new Row(i, board, color));
            }
        } else {
            for(int i = 7; i > -1; i--){
                rows.add(new Row(i, board, color));
            }
        }
    }

    /**
     * Used to get the iterator of the List
     * @return iterator for the List
     */
    @Override
    public Iterator<Row> iterator() {
        return rows.iterator();
    }
}
