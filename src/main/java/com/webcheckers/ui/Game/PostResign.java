package com.webcheckers.ui.Game;


import com.google.gson.Gson;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.*;
import spark.*;
import com.webcheckers.util.*;

import java.net.URLDecoder;

/**
 * SWEN-261
 * PostSubmitTurn.java
 * Team: \v
 * Purpose: Used to change whose turn it is
 */
public class PostResign implements Route {

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    /**
     * Constructor for the class
     *
     * @param templateEngine the template engine for the game
     * @param gameCenter     the gameCenter
     */
    public PostResign(TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
    }

    /**
     * Changes the turn
     *
     * @param request  The request to use
     * @param response the response to edit
     * @return Json
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        Player user = session.attribute("user");
        CheckerGame game = gameCenter.getGame(user);
        game.setResigned(user.getColor());
        user.setInGame(false);
        Message mess = Message.info("Resign accepted");
        Gson gson = new Gson();
        if (user.getCheckerGame().getCurrentTurn() == user.getColor()) {
            game.changeCurrentTurn();
        } else {

        }
        return gson.toJson(mess);
    }
}