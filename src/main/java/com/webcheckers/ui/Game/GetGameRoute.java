package com.webcheckers.ui.Game;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.CheckerGame;
import com.webcheckers.ui.BoardView;
import com.webcheckers.util.View;
import com.webcheckers.util.Message;
import spark.TemplateEngine;
import java.util.Objects;
import java.util.logging.Logger;
import spark.*;
import com.webcheckers.model.Player;
import java.util.*;


/**
 * SWEN-261
 * GetGameRoute.java
 * Team: \v
 * Purpose: Redirect to the game page
 */
public class GetGameRoute implements Route{
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
    private static final Message GAME_MSG = Message.info("This is not a game");

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;


    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine the HTML template rendering engine
     */
    public GetGameRoute(final TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        //
        LOG.config("GetGameRoute is initialized.");
    }

    /**
     * Render the WebCheckers Game page.
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Game page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();
        Session session = request.session();

        Player user = session.attribute("user");

        CheckerGame checkerGame;
        if(user != null && !user.isInGame()) {
            String opponentName = request.queryParams("username");
            Player opponent = gameCenter.getPlayer(opponentName);
            if(opponent.isInGame()){
                user.setHasError(true);
                response.redirect("/");
                return null;
            }
            checkerGame = gameCenter.startGame(user, opponent);
        } else {
            checkerGame = gameCenter.getGame(user);
        }

        vm.put("message",WELCOME_MSG);
        vm.put("title",GAME_MSG);
        vm.put("viewMode", View.PLAY);
        vm.put("currentUser", user);
        vm.put("redPlayer", checkerGame.getRedPlayer());
        vm.put("whitePlayer", checkerGame.getWhitePlayer());
        vm.put("activeColor", checkerGame.getCurrentTurn());
        vm.put("board", new BoardView(checkerGame.getBoard(), user.getColor()));
        vm.put("username", user.getName());

        vm.put("style", "css/style.css");
        if (user.getTheme().equals("dark")){
            vm.put("style", "css/darkmode.css");
        }

        Gson g = new Gson();
        String isGameOver = gameCenter.isGameOver(user);
        if (isGameOver.equals("Game not over")){
            // do nothing
        } else if (isGameOver.equals("Red wins")){
            final Map<String, Object> modeOptions = new HashMap<>(2);
            String red = gameCenter.getGame(user).getRedPlayer().getName();
            String white = gameCenter.getGame(user).getWhitePlayer().getName();
            modeOptions.put("isGameOver", true);
            modeOptions.put("gameOverMessage", red + " has captured all of " + white + "'s pieces.");
            vm.put("modeOptionsAsJSON", g.toJson(modeOptions));
        } else if (isGameOver.equals("White wins")){
            final Map<String, Object> modeOptions = new HashMap<>(2);
            String red = gameCenter.getGame(user).getRedPlayer().getName();
            String white = gameCenter.getGame(user).getWhitePlayer().getName();
            modeOptions.put("isGameOver", true);
            modeOptions.put("gameOverMessage", white + " has captured all of " + red + "'s pieces.");
            vm.put("modeOptionsAsJSON", g.toJson(modeOptions));
        } else if (isGameOver.equals("White resigned")) {
            final Map<String, Object> modeOptions = new HashMap<>(2);
            String white = gameCenter.getGame(user).getWhitePlayer().getName();
            modeOptions.put("isGameOver", true);
            modeOptions.put("gameOverMessage", white + " resigned.");
            vm.put("modeOptionsAsJSON", g.toJson(modeOptions));
        } else if (isGameOver.equals("Red resigned")) {
            final Map<String, Object> modeOptions = new HashMap<>(2);
            String red = gameCenter.getGame(user).getRedPlayer().getName();
            modeOptions.put("isGameOver", true);
            modeOptions.put("gameOverMessage", red + " resigned.");
            vm.put("modeOptionsAsJSON", g.toJson(modeOptions));
        } else {
            System.out.println("You shouldn't be here (GetGameRoute.handle())");
        }

        // render the View
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}

