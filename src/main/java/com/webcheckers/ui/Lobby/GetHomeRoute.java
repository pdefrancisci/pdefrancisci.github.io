package com.webcheckers.ui.Lobby;

import java.util.*;
import java.util.logging.Logger;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Player;
import spark.*;

import com.webcheckers.util.Message;

/**
 * SWEN-261
 * GetHomeRoute.java
 * Team: \v
 * Purpose: UI controller to GET the homepage
 */
public class GetHomeRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());


    public static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
    public static final Message ERROR_MSG = Message.error("That player is already in a game!");

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public GetHomeRoute(final TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        //
        LOG.config("GetHomeRoute is initialized.");
    }

    /**
     * Render the WebCheckers Home page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetHomeRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");

        Session session = request.session();
        Player user = session.attribute("user");

        // display a user message in the Home page
        if(user != null && user.isHasError()){
            vm.put("message", ERROR_MSG);
        } else {
            vm.put("message", WELCOME_MSG);
        }

        vm.put("style", "/css/style.css");
        if( user != null && user.getTheme().equals("dark")) {
            vm.put("style", "/css/darkmode.css");
        }

        // if the user has not signed in
        if(session.isNew() || user == null){
            vm.put("username", null);
            vm.put("num_players", gameCenter.getAmountPlayersInLobby());
            return templateEngine.render(new ModelAndView(vm, "home.ftl"));
        }
        // if the user has signed in
        else {
            // if the player has been selected for a game
            if(user.isInGame()){
                response.redirect("/game");
                return null;
            } else {
                // add the username to the vm
                String username = user.getName();
                vm.put("username", username);

                // add the opponent list to the vm
                List<String> possibleOpponents = new LinkedList<>();
                for(Player player : gameCenter.getPlayersInLobby()){
                    if(!player.getName().equals(username)){
                        possibleOpponents.add(player.getName());
                    }
                }
                vm.put("players", possibleOpponents);
                return templateEngine.render(new ModelAndView(vm, "home.ftl"));
            }
        }
    }
}
