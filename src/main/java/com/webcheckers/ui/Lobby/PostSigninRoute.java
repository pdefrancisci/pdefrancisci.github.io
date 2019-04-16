package com.webcheckers.ui.Lobby;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * SWEN-261
 * PostSinginRoute.java
 * Team: \v
 * Purpose: The (@code POST /signin) route handeler
 */
public class PostSigninRoute implements Route {

    private static final String SIGNIN_PARAM = "username";
    private static final String VIEW_NAME = "signin.ftl";
    private static final String NAME_TAKEN = "That username has been taken!";
    private static final String INVALID_NAME = "That name is invalid, choose another.";
    private static final String MESSAGE_ATTR = "message";
    private static final String HOME_ROUTE = "/";


    private GameCenter gameCenter;
    private final TemplateEngine templateEngine;


    /**
     * Constructor for a PostSigninROute
     * @param templateEngine Template engine of the game
     * @param gameCenter Game center being used to control the game
     */
    public PostSigninRoute( TemplateEngine templateEngine, GameCenter gameCenter){
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
    }


    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object handle(Request request, Response response){
        //LOG.finer("GetSigninRoute is invoked.");

        //start building the View-Model for Lobby page
        final Map<String, Object> vm = new HashMap<>();

        //retrieve signin parameter
        final String username = request.queryParams(SIGNIN_PARAM);

        Player player = new Player(username);

        ModelAndView mv;
        switch (gameCenter.signInPlayer(player)){
            case TAKEN:
                mv = error(vm, NAME_TAKEN);
                break;

            case INVALID:
                mv = error(vm, INVALID_NAME);
                break;

            case VALID:
                valid(request, response, player);
                return null;

            default:
                // This shouldn't happen
                throw new NoSuchElementException("Something went very wrong in PostSigninRoute");

        }

        return templateEngine.render(mv);
    }


    /**
     * Used to show the error message in the game
     * @param vm the variable map used for display
     * @param message The error message to be displayed
     * @return Model view containing the correct vm
     */
    private ModelAndView error(final Map<String, Object> vm, final String message){
        vm.put(MESSAGE_ATTR, message);
        return new ModelAndView(vm, VIEW_NAME);
    }


    /**
     * Finish sign in and redirects user to the home page
     * @param request Used to get the current session
     * @param response The response to the request
     * @param player Player that was just created
     */
    private void valid(Request request, Response response, Player player){
        Session session = request.session();
        session.attribute("user", player);
        response.redirect(HOME_ROUTE);
    }
}
