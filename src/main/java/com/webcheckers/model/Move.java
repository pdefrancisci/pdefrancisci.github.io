package com.webcheckers.model;

import spark.ModelAndView;

/**
 * SWEN-261
 * Move.java
 * Team: \v
 * Purpose: Represents a move, storing the start and end position
 */
public class Move {

    private Position start;
    private Position end;


    @Override
    public int hashCode() {
        return ((start.hashCode()*31) + end.hashCode());
    }

    /**
     * Constructor for a single move from one position to another
     *
     * @param start starting location
     * @param end ending location
     */
    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }


    /**
     * Getter for the start position
     *
     * @return the start position of the move
     */
    public Position getStart() {
        return start;
    }


    /**
     * Getter for the end position
     *
     * @return the end position of the move
     */
    public Position getEnd() {
        return end;
    }


    /**
     * toString for a move
     *
     * @return The formatted version of the start and end position
     */
    @Override
    public String toString() {
        return "Move{" + "start=" + start + ", end=" + end + '}';
    }


    /**
     * equals method for a move
     *
     * @return true if o = this, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof Move){
            Move obj = (Move)o;
            /*System.out.println("    Move");
            System.out.println("obj = " + obj.toString());
            System.out.println("this = " + this.toString());
            System.out.println("equals() = " + new Boolean(this.start.equals(obj.getStart()) && this.end.equals(obj.getEnd())).toString());
            */
            return this.start.equals(obj.getStart()) && this.end.equals(obj.getEnd());
        }
        return false;
    }
}
