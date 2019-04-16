package com.webcheckers.ui.Game;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.CheckerGame;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

public class PostSpectateCheckTurn implements Route {
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    public PostSpectateCheckTurn(TemplateEngine templateEngine, GameCenter gameCenter){
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
//need to figure out how to get the player we are spectating here
        String id = request.queryParams("gameID");
        System.out.println("ID = " + id);

        CheckerGame game = new CheckerGame(new Player("bob"), new Player("s"));


        //serialize the message
        Gson g = new Gson();
        Message mess = Message.info("NULL");

        if(!(game.gameOver().equals("Game not over"))){
            mess = Message.info("info:true");
        }
        else{
            if(game.getChange()) {
                mess = Message.info("true");
                game.setChange(false);
            }
            else{
                mess = Message.info("false");
            }
        }

        String json = g.toJson(mess);
        //respect the full send
        return mess;
    }
}
