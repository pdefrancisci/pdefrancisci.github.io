package com.webcheckers.ui.Game;

import com.google.gson.Gson;
import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class PostBackupMove implements Route {

    private final GameCenter gameCenter;
    private final Gson gson;

    public PostBackupMove(GameCenter gameCenter, Gson gson){
        this.gameCenter = gameCenter;
        this.gson = gson;
    }


    /**
     * Handles the user backing up and undoing a move
     *
     * @param request the request to handle
     * @param response where to put the response
     * @return json message to display
     */
    @Override
    public Object handle(Request request, Response response){
        Session session = request.session();
        Player user = session.attribute("user");

        // try to backup the move and report if successful or not
        Message message;
        if(gameCenter.backupMove(user))
            message = Message.info("Backed up and undid that last move.");
        else
            message = Message.error("Can't backup anymore!");

        return gson.toJson(message);
    }
}
