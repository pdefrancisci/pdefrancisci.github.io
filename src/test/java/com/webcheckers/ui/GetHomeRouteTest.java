package com.webcheckers.ui;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.webcheckers.application.GameCenter;
import com.webcheckers.TemplateEngineTester;
import com.webcheckers.model.Player;
import com.webcheckers.ui.Lobby.GetHomeRoute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import java.util.LinkedList;
import java.util.List;

/**
 * The unit test suite for the {@link GetHomeRoute} component.
 *
 * @author Anthony DiCarlo
 */
@Tag("UI-tier")
public class GetHomeRouteTest {

    /**
     * The component-under-test (CuT).
     *
     * <p>
     * This is a stateless component so we only need one.
     * The {@link GameCenter} component is thoroughly tested so
     * we can use it safely as a "friendly" dependency.
     */
    private GetHomeRoute CuT;

    // friendly objects
    private GameCenter gameCenter;

    // mock objects
    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;

    /**
     * Setup new mock objects for each test.
     */
    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        // create a unique CuT for each test
        // the GameCenter is friendly but the engine mock will need configuration
        gameCenter = new GameCenter();
        CuT = new GetHomeRoute(engine, gameCenter);
    }

    /**
     * Test that CuT shows the Home view when the session is brand new.
     */
    @Test
    public void new_session() {
        // set up the scenario: a brand new user has opened the sit
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data
        testHelper.assertViewModelAttribute("title", "Welcome!");
        testHelper.assertViewModelAttribute("message", GetHomeRoute.WELCOME_MSG);
        testHelper.assertViewModelAttribute("username", null);
        testHelper.assertViewModelAttribute("num_players", gameCenter.getAmountPlayersInLobby());
        //   * test view name
        testHelper.assertViewName("home.ftl");
    }


    /**
     * Test that CuT works when the user has signed in
     */
    @Test
    public void existing_session() {
        // create a fake player that the user signed in as
        Player player = mock(Player.class);
        when(session.attribute("user")).thenReturn(player);
        when(player.getName()).thenReturn("FakeUsername");
        List<Player> no_players = new LinkedList<>();

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data
        testHelper.assertViewModelAttribute("title", "Welcome!");
        testHelper.assertViewModelAttribute("message", GetHomeRoute.WELCOME_MSG);
        testHelper.assertViewModelAttribute("username", "FakeUsername");
        testHelper.assertViewModelAttribute("players", no_players);
        //   * test view name
        testHelper.assertViewName("home.ftl");
    }
}
