package com.webcheckers.application;

import com.webcheckers.model.CheckerGame;
import com.webcheckers.model.Move;
import com.webcheckers.util.Color;
import com.webcheckers.model.Player;
import com.webcheckers.util.SigninStatus;

import java.util.HashMap;
import java.util.Set;

/**
 * SWEN-261
 * GameCenter.java
 * Team: \v
 * Purpose: Stores players and games
 */
public class GameCenter {

    private PlayerLobby playerLobby;
    private HashMap<Player, CheckerGame> games;

    /**
     * Constructor, creates empty player lobby and games map
     */
    public GameCenter(){
        this.playerLobby = new PlayerLobby();
        this.games = new HashMap<>();
    }


    /**
     * Start a game between two players and store the game in the games map under both the red and white players names
     * @param redPlayer player 1
     * @param whitePlayer player 2
     */
    public CheckerGame startGame(Player redPlayer, Player whitePlayer){
        CheckerGame checkerGame = new CheckerGame(redPlayer, whitePlayer);
        redPlayer.joinGame(Color.RED, checkerGame);
        whitePlayer.joinGame(Color.WHITE, checkerGame);
        games.put(redPlayer, checkerGame);
        games.put(whitePlayer, checkerGame);
        return checkerGame;
    }


    /**
     * get game associated with a player
     * @param player player to find associated game
     * @return game associated with player
     */
    public CheckerGame getGame(Player player){
        return this.games.get(player);
    }


    /**exception
     * Get the amount of players in the lobby at any given time
     *
     * @return integer amount of players
     */
    public int getAmountPlayersInLobby(){
        return this.playerLobby.getPlayers().size();
    }


    /**
     * Sign in a player into the lobby
     *
     * @param player player to sign in
     */
    public SigninStatus signInPlayer(Player player){
        return playerLobby.signIn(player);
    }


    /**
     * Sign out a player in the lobby
     *
     * @param player player to sign out
     */
    public void signOutPlayer(Player player){
        playerLobby.signOut(player);
    }


    /**
     * Get the set of players that are currently in the lobby
     *
     * @return the set of players in lobby
     */
    public Set<Player> getPlayersInLobby(){
        return playerLobby.getPlayers();
    }


    /**
     * Get the Player object representation of a given player
     *
     * @return the Player with the given username
     */
    public Player getPlayer(String username){
        return playerLobby.getPlayer(username);
    }


    /**
     * Check to see if a move a player made is valid or not
     *
     * @param player player who made the move
     * @param move the move that was made
     * @return "valid" if valid, otherwise the error message is returned
     */
    public String validateMove(Player player, Move move){
        return getGame(player).validateMove(player.getColor(), move);
    }


    /**
     * Backup the last move the player made
     *
     * @param player player who made the request
     * @return true if successful, false otherwise
     */
    public boolean backupMove(Player player){
        return getGame(player).backupMove();
    }


    /**
     * See if game is over, if so return who has one, otherwise return appropriate message
     */
    public String isGameOver(Player player){ return getGame(player).gameOver(); }
}
