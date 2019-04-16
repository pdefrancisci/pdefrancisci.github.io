package com.webcheckers.ui.Game;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Board;
import com.webcheckers.model.CheckerGame;
import com.webcheckers.model.Player;
import com.webcheckers.ui.BoardView;
import com.webcheckers.ui.Lobby.PostSigninRoute;
import com.webcheckers.ui.WebServer;
import com.webcheckers.util.Color;
import com.webcheckers.util.Message;
import com.webcheckers.util.View;
import spark.*;

import java.util.HashMap;
import java.util.Map;


public class GetSpectateRoute implements Route {

    private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
    private static final Message GAME_MSG = Message.info("This is not a game");


    private GameCenter gameCenter;
    private TemplateEngine templateEngine;

    public GetSpectateRoute( TemplateEngine templateEngine,GameCenter gameCenter){
        this.gameCenter=gameCenter;
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception{
        final Map<String, Object> vm = new HashMap<>();
        final Session session = request.session();
        Player player = session.attribute("user");

        if(player != null && player.isInGame()){
            vm.put("redPlayer", gameCenter.getGame(player).getRedPlayer());
            vm.put("currentUser",player);
            vm.put("viewMove", View.PLAY);
            vm.put("activeColor", Color.RED);
            Board board = gameCenter.getGame(player).getBoard();
            vm.put("board",board);
            vm.put("style", "css/style.css");
            if (player.getTheme().equals("dark")){
                vm.put("style", "css/darkmode.css");
            }
            player.setSpectating(false);
            response.redirect(WebServer.GAME_URL);
            return templateEngine.render(new ModelAndView(vm, "game.ftl"));
        }

        if (!(player.isSpectating())){
            player.setSpectating(true);
        }

        String opponentName = request.queryParams("username");

        Player opponent = gameCenter.getPlayer(opponentName);

        CheckerGame game = gameCenter.getGame(opponent);
        //if the game has ended
        if(!(game.gameOver().equals("Game not over"))){
            vm.put("title", "Welcome!");
            vm.put("num_players",gameCenter.getAmountPlayersInLobby());
            player.setSpectating(false);
            vm.put("style", "css/style.css");
            if (player.getTheme().equals("dark")){
                vm.put("style", "css/darkmode.css");
            }
            response.redirect("/");
            return templateEngine.render(new ModelAndView(vm,"home.ftl"));
        }
        vm.put("message",WELCOME_MSG);
        vm.put("title",GAME_MSG);
        vm.put("viewMode", View.SPECTATOR);
        vm.put("whitePlayer", game.getWhitePlayer());
        vm.put("activeColor", game.getCurrentTurn());
        vm.put("currentUser", player);
        vm.put("redPlayer", game.getRedPlayer());
        vm.put("board", new BoardView(game.getBoard(), opponent.getColor()));
        vm.put("username", player.getName());
        vm.put("style", "css/style.css");
        if (player.getTheme().equals("dark")){
            vm.put("style", "css/darkmode.css");
        }
        return templateEngine.render(new ModelAndView(vm, "spectate.ftl"));
    }

}




