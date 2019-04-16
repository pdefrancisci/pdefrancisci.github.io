package com.webcheckers.model;

import com.webcheckers.util.Color;
import com.webcheckers.util.Type;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * SWEN-261
 * Board.java
 * Team: \v
 * Purpose: Keeps track of a board for the checkers game
 */
public class Board {
    // 2d array that represents the board
    private Space[][] board;

    /**
     * Creates a board and populates it with pieces
     */
    public Board(){
        this.board = new Space[8][8];

        // populate the board with different spaces
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = new Space(i, j);
                if( ( i + j ) % 2 == 1 ){
                    board[i][j].setBlack();
                }
            }
        }

        // initialize the board with the checkers in the right spot
        // Adds white pieces
        board[0][1].setPiece(new Piece(Color.WHITE, Type.SINGLE));
        board[0][3].setPiece(new Piece(Color.WHITE, Type.SINGLE));
        board[0][5].setPiece(new Piece(Color.WHITE, Type.SINGLE));
        board[0][7].setPiece(new Piece(Color.WHITE, Type.SINGLE));
        board[1][0].setPiece(new Piece(Color.WHITE, Type.SINGLE));
        board[1][2].setPiece(new Piece(Color.WHITE, Type.SINGLE));
        board[1][4].setPiece(new Piece(Color.WHITE, Type.SINGLE));
        board[1][6].setPiece(new Piece(Color.WHITE, Type.SINGLE));
        board[2][1].setPiece(new Piece(Color.WHITE, Type.SINGLE));
        board[2][3].setPiece(new Piece(Color.WHITE, Type.SINGLE));
        board[2][5].setPiece(new Piece(Color.WHITE, Type.SINGLE));
        board[2][7].setPiece(new Piece(Color.WHITE, Type.SINGLE));

        // adds red pieces
        board[7][0].setPiece(new Piece(Color.RED, Type.SINGLE));
        board[7][2].setPiece(new Piece(Color.RED, Type.SINGLE));
        board[7][4].setPiece(new Piece(Color.RED, Type.SINGLE));
        board[7][6].setPiece(new Piece(Color.RED, Type.SINGLE));
        board[6][1].setPiece(new Piece(Color.RED, Type.SINGLE));
        board[6][3].setPiece(new Piece(Color.RED, Type.SINGLE));
        board[6][5].setPiece(new Piece(Color.RED, Type.SINGLE));
        board[6][7].setPiece(new Piece(Color.RED, Type.SINGLE));
        board[5][0].setPiece(new Piece(Color.RED, Type.SINGLE));
        board[5][2].setPiece(new Piece(Color.RED, Type.SINGLE));
        board[5][4].setPiece(new Piece(Color.RED, Type.SINGLE));
        board[5][6].setPiece(new Piece(Color.RED, Type.SINGLE));
    }


    /**
     * Getter for the board
     * @return A filled in board
     */
    public Space[][] getBoard() {
        return board;
    }


    /**
     * Getter for an element of the board
     *
     * @param row item's row
     * @param col item's col
     * @return the Space at [row][col]
     */
    public Space getItem(int row, int col){
        try {
            return board[row][col];
        }
        catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }


    public Board copy(){
        Board copiedBoard = new Board();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++)
                copiedBoard.getBoard()[i][j] = board[i][j].copySpace();
        }
        return copiedBoard;
    }
}
