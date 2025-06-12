package com.tulli.example.modules;

import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChainedModule  extends ServletModule {

    protected void configureServlets() {
        serve("/chained").with(ChainedServlet.class);
        install(new Chain());

    }
}

class Chain extends ServletModule {

    @Override
    protected void configureServlets() {
        serve("/chain").with(ChainedServlet.class);
    }
}

@Singleton
class ChainedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().write("Served by ChainedServlet with the url: " + req.getRequestURI());
    }
}