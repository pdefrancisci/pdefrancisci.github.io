package com.webcheckers.ui.Game;

import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.net.URLDecoder;

/**
 * SWEN-261
 * PostValidateMove.java
 * Team: \v
 * Purpose: Used to validate the move
 */
public class PostValidateMove implements Route {

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    /**
     * Constructor for the class
     * @param templateEngine
     * @param gameCenter
     */
    public PostValidateMove(TemplateEngine templateEngine, GameCenter gameCenter){
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
    }

    /**
     * Used to validate the move
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        // get the player who made this move
        Session session = request.session();
        Player user = session.attribute("user");

        // get the user's move from the request
        Gson gson = new Gson();
        final String moveData = URLDecoder.decode(request.body(), StandardCharsets.UTF_8.name()).substring(11);
        //System.out.println("*******");
        //System.out.println(moveData);
        //System.out.println("*******");
        Move move = gson.fromJson(moveData, Move.class);

        // try to validate the move and return the results
        String validation = gameCenter.validateMove(user, move);
        Message message;
        if(!validation.contains("Invalid"))
            message = Message.info("valid move");
        else
            message = Message.error(validation);

        //System.out.println("***Validation Results***");
        //System.out.println(validation);
        //System.out.println("************************");




        return gson.toJson(message);
    }
}
