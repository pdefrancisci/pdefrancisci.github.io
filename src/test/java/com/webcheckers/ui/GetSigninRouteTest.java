package com.webcheckers.ui;

import com.webcheckers.TemplateEngineTester;
import static org.mockito.ArgumentMatchers.any;
import com.webcheckers.ui.Lobby.GetSigninRoute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * SWEN-261
 * GetSigninRouteTest.java
 * Team: \v
 * Purpose: The (@code GET / signin) route handler test
 */

@Tag("UI-tier")
public class GetSigninRouteTest {

    private TemplateEngine templateEngine;
    private GetSigninRoute CuT;
    private Request request;
    private Response response;
    private Session session;

    /**
     * Setup new mock objects for each test
     */
    @BeforeEach
    public void setup(){
        templateEngine = mock(TemplateEngine.class);
        session = mock(Session.class);
        response = mock(Response.class);
        request = mock(Request.class);
        when(request.session()).thenReturn(session);
        CuT = new GetSigninRoute(templateEngine);
    }


    /**
     * Test that the signin page will create a new page if none exist
     */
    @Test
    public void new_session() {

        final TemplateEngineTester testHelper = new TemplateEngineTester(); //set up scenario
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);  //invoke test

        testHelper.assertViewModelExists(); //Model is non-null
        testHelper.assertViewModelIsaMap(); //Model is a Map

        //model contains necessary View-Model data
        testHelper.assertViewModelAttribute("message",
                "Enter username that will strike fear in the " +
                        "hearts of your opponents into the field below.");

        testHelper.assertViewName("signin.ftl");    //test view name

    }

}
