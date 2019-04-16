package com.webcheckers.ui.Lobby;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetDarkRoute implements Route {

    private GameCenter gameCenter;

    public GetDarkRoute(GameCenter gameCenter){
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String username = request.queryParams("darkmode");
        Player player = this.gameCenter.getPlayer(username);
        player.toggleTheme();
        response.redirect("/");
        return null;
    }
}
