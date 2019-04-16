package com.webcheckers.ui.Lobby;

import com.webcheckers.application.GameCenter;
import spark.*;

import java.util.HashMap;
import java.util.Map;

/**
 * SWEN-261
 * GetSignoutRoute.java
 * Team: \v
 * Purpose: The (@code GET /signout) route handler
 */
public class GetSignoutRoute implements Route {

    public static final String HOME_PATH = "/";

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    /**
     * Constructor
     * @param templateEngine
     * @param gameCenter
     */
    public GetSignoutRoute(final TemplateEngine templateEngine, final GameCenter gameCenter){
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
    }


    /**
     * Used to display the signout page
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Map<String, Object> vm = new HashMap<>();

        Session session = request.session();

        gameCenter.signOutPlayer(session.attribute("user"));
        session.removeAttribute("user");

        response.redirect(HOME_PATH);
        return null;
    }
}
