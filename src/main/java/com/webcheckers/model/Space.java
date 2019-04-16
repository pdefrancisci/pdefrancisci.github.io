package com.webcheckers.model;

import com.webcheckers.util.Color;
import com.webcheckers.util.SpaceColor;

/**
 * SWEN-261
 * Space.java
 * Team: \v
 * Purpose: This class represents a space on the board
 */
public class Space {

    private final int row;
    private final int col;
    private Piece piece;
    private final int cellIdx;
    private SpaceColor spaceColor;

    /**
     * Constructor for the space
     * @param row row of the space
     * @param col col of the space
     */
    public Space(int row, int col){
        this.row = row;
        this.col = col;
        this.cellIdx = col;
        this.piece = null;
        this.spaceColor = SpaceColor.WHITE;
    }

    /**
     * Getter for the col
     * @return col
     */
    public int getCol() {
        return col;
    }

    /**
     * Getter for the row
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets a piece on this space
     * @param piece the piece that belongs on this space
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Used to see if there is a piece on this space
     * @return True if there is, false if there isn't
     */
    public boolean hasPiece(){
        return piece != null;
    }

    /**
     * Getter for the piece
     * @return The piece that's on this space
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * toString function for Space
     * @return Pretty prints the color of the piece on this space
     */
    @Override
    public String toString() {
        if (piece == null){
            return "NONE";
        }
        else if(piece.getColor() == Color.WHITE){
            return "WHITE";
        }
        else {
            return "RED";
        }
    }


    /**
     * Sets the spaceColor to BLACK
     */
    public void setBlack(){
        this.spaceColor = SpaceColor.BLACK;
    }


    /**
     * Determines if the space is occupied
     * @return true if this is a valid space to move to, false otherwise
     */
    public boolean isValid(){
        if(this.piece != null){
            return false;
        }
        return this.spaceColor == SpaceColor.BLACK;
    }


    /**
     * Getter for the cell index
     * @return the cell index
     */
    public int getCellIdx(){
        return cellIdx;
    }


    /**
     * equals for Space
     * @param o object to compare to this
     * @return true if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Space){
            Space other = (Space)o;
            return this.col == other.col && this.row == other.row;
        }
        return false;
    }


    /**
     * Promote the piece on this space to a king
     */
    public void promoteToKing(){
        this.piece.promoteToKing();
    }


    public Space copySpace(){
        Space newSpace = new Space(row, col);
        if(this.piece != null)
            newSpace.setPiece(this.piece.copyPiece());
        if(this.spaceColor == SpaceColor.BLACK)
            newSpace.setBlack();
        return newSpace;
    }
}
