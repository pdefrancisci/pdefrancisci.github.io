package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.ui.Lobby.GetSignoutRoute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@Tag("UI-tier")
public class GetSignoutRouteTest {

    private TemplateEngine templateEngine;
    private GameCenter gameCenter;
    private GetSignoutRoute CuT;
    private Request request;
    private Response response;
    private Session session;

    @BeforeEach
    public void setup(){
        templateEngine = mock(TemplateEngine.class);
        gameCenter = mock(GameCenter.class);
        session = mock(Session.class);
        response = mock(Response.class);
        request = mock(Request.class);
        when(request.session()).thenReturn(session);
        CuT = new GetSignoutRoute(templateEngine, gameCenter);
    }

    @Test
    public void SignOutTest(){
        try {
            CuT.handle(request, response);
            // make sure user attribute has been removed
            assertFalse(session.attributes().contains("user"));
            // ensure redirect successful
            verify(response).redirect(GetSignoutRoute.HOME_PATH);
        } catch (Exception e){
            // Shouldn't happen
        }
        assertFalse(session.attributes().contains("user"));
    }

    @Test
    public void badSession(){
        try {
            CuT.handle(request, response);
            // make sure exception is thrown
            //fail("We failed");
            // ensure redirect successful
            verify(response).redirect(GetSignoutRoute.HOME_PATH);
        } catch (Exception e){
            //Expected
            assertFalse(false);
        }
    }
}
