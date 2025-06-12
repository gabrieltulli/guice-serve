package com.tulli.example.modules;

import com.google.inject.servlet.ServletModule;
import com.tulli.example.servlets.SecondServlet;

public class SecondModule extends ServletModule {

    protected void configureServlets() {
        serve("/other").with(SecondServlet.class);
    }
}
