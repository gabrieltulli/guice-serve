package com.tulli.example.modules;

import com.google.inject.servlet.ServletModule;
import com.tulli.example.servlets.DefaultServlet;

public class DefaultModule extends ServletModule {

    protected void configureServlets() {
        serve("/another").with(DefaultServlet.class);
    }
}
