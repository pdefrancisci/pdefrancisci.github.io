package com.webcheckers.ui.Lobby;

import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * SWEN-261
 * GetSigninRoute.java
 * Team: \v
 * Purpose: The (@code GET /signin) route handler
 */
public class GetSigninRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());
    private final TemplateEngine templateEngine;


    /**
     * Constructor
     * @param templateEngine templateEngine
     */
    public GetSigninRoute(final TemplateEngine templateEngine){
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("GetSigninRoute is initialized.");
    }


    /**
     * Displays the signin page
     * @param request
     * @param response
     * @return template engine
     */
    @Override
    public Object handle(Request request, Response response){
        LOG.finer("GetSigninRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();
        vm.put("style", "css/style.css");
        vm.put("message", "Enter username that will strike fear in the hearts of your opponents into the field below.");

        return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
    }
}
