package com.webcheckers.model;

/**
 * SWEN-261
 * Position.java
 * Team: \v
 * Purpose: This class defines a position on the board
 */
public class Position {

    private int row;
    private int cell;

    @Override
    public int hashCode() {
        return row*31+cell;
    }

    /**
     * Create a new position that represents a space on the board
     *
     * @param row row of this position
     * @param cell column of this position
     */
    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    /**
     * Getter for the row of this position
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter for the col of this position
     *
     * @return col
     */
    public int getCell() {
        return cell;
    }


    /**
     * An equals method that returns true when the row and col are the same
     *
     * @param o object we are comparing to
     * @return true if same and false otherwise
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Position){
            Position other = (Position)o;
            /*System.out .println("    Position");
            System.out.println("other = " + other.toString());
            System.out.println("this = " + this.toString());
            System.out.println("equals() = " + new Boolean(other.cell == this.cell && other.row == this.row).toString());
            */
            return other.cell == this.cell && other.row == this.row;
        }
        return false;
    }


    /**
     * String representation of the position
     *
     * @return Formatted string containing the position
     */
    @Override
    public String toString() {
        return "Position{" + "row=" + row + ", cell=" + cell + '}';
    }
}
