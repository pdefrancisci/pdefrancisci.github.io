package com.webcheckers.ui.Game;


import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.*;
import spark.*;
import com.webcheckers.util.*;

/**
 * SWEN-261
 * PostSubmitTurn.java
 * Team: \v
 * Purpose: Used to change whose turn it is
 */
public class PostSubmitTurn implements Route {

    private final GameCenter gameCenter;

    /**
     * Constructor for the class
     * @param gameCenter the gameCenter
     */
    public PostSubmitTurn(GameCenter gameCenter) {
        this.gameCenter = gameCenter;
    }

    /**
     * Changes the turn
     * @param request The request to use
     * @param response the response to edit
     * @return Json
     */
    @Override
    public Object handle(Request request, Response response){
        // get the player who made this move
        Session session = request.session();
        Player user = session.attribute("user");

        // don't let them end if they can still jump
        Message mess;
        if(gameCenter.getGame(user).getTurnType() == PlayerMoves.JUMP &&
                gameCenter.getGame(user).isTurnOver(user.getColor())) {
            mess = Message.error("You can still jump again!");
        }
        else {
            mess = Message.info("Turn submitted!");
            CheckerGame game = gameCenter.getGame(user);
            game.changeCurrentTurn();
        }

        return new Gson().toJson(mess);
    }
}