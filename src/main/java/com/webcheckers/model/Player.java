package com.webcheckers.model;


import com.webcheckers.util.Color;

/**
 * SWEN-261
 * Player.java
 * Team: \v
 * Purpose: This class represents a player in our system, each player has a unique name
 */
public class Player {

    private final String name;
    private boolean inGame;
    private Color color;
    private CheckerGame checkerGame;
    private boolean hasError;
    private boolean isSpectating;
    private String theme;

    /**
     * Used to create a player with a namepackage com.webcheckers.ui.model;
     *
     * @param name the name of the player
     */
    public Player(String name){
        this.name = name;
        this.inGame = false;
        this.color = Color.NONE;
        this.checkerGame = null;
        this.hasError = false;
        this.isSpectating = false;
        this.theme = "light";
    }


    /**
     * get player's preferred theme
     * @return string, either 'light' or 'dark'
     */
    public String getTheme(){
        return this.theme;
    }


    /**
     * toggle between light and dark theme
     */
    public void toggleTheme(){
        if (this.getTheme().equals("light")){
            this.theme = "dark";
        } else {
            this.theme = "light";
        }
    }


    /**
     * Getter for return this Player's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }


    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    /**
     * Used to connect a player to a CheckerGame
     *
     * @param color the color that the player is assigned
     */
    public void joinGame(Color color, CheckerGame checkerGame){
        this.inGame = true;
        this.color = color;
        this.checkerGame = checkerGame;
    }

    /**
     * Used to determine if a player is in a game.
     *
     * @return inGame status
     */
    public boolean isInGame(){
        return inGame;
    }

    /**
     * Getter for the color of the player
     * @return color of the player
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter for the CheckerGame of the player
     * @return the CheckerGame
     */
    public CheckerGame getCheckerGame() {
        return checkerGame;
    }

    /**
     * Checks to see if two player names are equal
     * @param o object to be compared
     * @return True if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return this.name.equals(player.getName());
    }

    /**
     * Generates hash for Player using the hash of the player name
     * @return Strings hashcode of the player's name
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Setter for hasError
     * @param hasError true if there is an error, false if there isn't an error
     */
    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    /**
     * Getter for hasError
     * @return true if there is an error, false if there isn't
     */
    public boolean isHasError() {
        return hasError;
    }

    /**
     * Setter for spectating
     * @param bool boolean value to set isSpectating to
     */
    public void setSpectating(boolean bool){
        this.isSpectating=bool;
    }

    /**
     * Getter for isSpectating
     * @return true if the player is spectating, false otherwise
     */
    public boolean isSpectating() {
        return isSpectating;
    }

    /**
     * toString for a player
     * @return formatted String with the data from the player
     */
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' + ", inGame=" + inGame + ", color=" + color + '}';
    }
}
