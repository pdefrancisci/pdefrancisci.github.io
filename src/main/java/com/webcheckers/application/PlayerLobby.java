package com.webcheckers.application;


import com.webcheckers.model.Player;
import com.webcheckers.util.SigninStatus;

import java.util.HashSet;
import java.util.Set;

/**
 * SWEN-261
 * PlayerLobby.java
 * Team: \v
 * Purpose: Handles signing in and signing out
 */
public class PlayerLobby{

    //Signed in players
    private Set<Player> players;


    /**
     * The constructor for PlayerLobby
     */
    public PlayerLobby(){
        this.players = new HashSet<>();
    }


    /**
     * Get the set of all signed in players
     * @return set of players
     */
    public Set<Player> getPlayers() {
        return players;
    }


    /**
     * Given a player's username, get their player object
     *
     * @param playerName username of the player
     * @return Player with that name
     */
    public Player getPlayer(String playerName){
        for(Player player : players){
            if(player.getName().equals(playerName)){
                return player;
            }
        }
        // shouldn't happen
        return null;
    }


    /**
     * Determine whether player username is valid, taken, or invalid
     * @param player player to add to the lobby
     * @return status of acceptance
     */
    public SigninStatus signIn(Player player){

        // Check to see if the username has been taken
        if(players.contains(player)){
            return SigninStatus.TAKEN;
        }
        // TODO: Allow people to have spaces, *investigate further*
        // Check that the username doesn't have a double quote in it or the empty string
        if(player.getName().contains("\"") || player.getName().isEmpty() || player.getName().contains(" ")){
            return SigninStatus.INVALID;
        }

        // Valid player created!
        players.add(player);
        return SigninStatus.VALID;
    }


    /**
     * Remove the player from the set of signed in players
     * @param player the player to remove
     */
    public void signOut(Player player){
        players.remove(player);
    }
}
