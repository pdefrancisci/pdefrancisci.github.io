package com.webcheckers.model;

import com.webcheckers.util.Color;
import com.webcheckers.util.Type;

/**
 * SWEN-261
 * Piece.java
 * Team: \v
 * Purpose: Represents a piece on the board
 */
public class Piece {

    private Color color;
    private Type type;

    /**
     * Constructor for a piece
     * @param color The color of the piece
     * @param type The type of the piece
     */
    public Piece(Color color, Type type){
        this.color = color;
        this.type = type;
    }

    /**
     * Getter for the color
     * @return The color of the piece
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter for the type of the piece
     * @return The type of the piece
     */
    public Type getType() {
        return type;
    }

    /**
     * Determines if another object is equal to this object
     * @param o another object to compare
     * @return True if object is equal, False if it isn't
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Piece){
            Piece other = (Piece)o;
            return this.color == other.color && this.type == other.type;
        }
        return false;
    }


    /**
     * Turn this piece into a king
     */
    public void promoteToKing(){
        this.type = Type.KING;
    }


    public Piece copyPiece(){
        return new Piece(color, type);
    }
}
