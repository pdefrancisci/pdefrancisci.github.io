package com.webcheckers.ui.Game;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import com.google.gson.Gson;
import spark.*;

/**
 * SWEN-261
 * PostCheckTurn.java
 * Team: \v
 * Purpose: Used to determine whose turn it is in the game
 */
public class PostCheckTurn implements Route {

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    public PostCheckTurn(TemplateEngine templateEngine, GameCenter gameCenter){
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
    }


    /**
     * Handles the request
     * @param request The request to handle
     * @param response where to put the response
     * @return json
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        //Figure out which player this is
        Session session = request.session();
        Player user = session.attribute("user");

        //if there's no reason not to get a game, get a game
        CheckerGame game;
        if(true){
            game = gameCenter.getGame(user);
        }
        else{
            //do something else
        }

        //serialize the message
        Gson g = new Gson();
        //System.out.println(game.getCurrentTurn().toString());
        Message mess = Message.info(new Boolean(game.getCurrentTurn().toString().equals(user.getColor().toString())).toString());
        //System.out.println(mess);
        String json = g.toJson(mess);
        //System.out.println(json);

        //just gonna send it
        return json;
    }
}
