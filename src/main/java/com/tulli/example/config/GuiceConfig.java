package com.tulli.example.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.tulli.example.Util;
import com.tulli.example.modules.ChainedModule;
import com.tulli.example.modules.DefaultModule;
import com.tulli.example.modules.SecondModule;
import com.tulli.example.servlets.AllServlet;
import com.tulli.example.servlets.FirstServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

public class GuiceConfig extends GuiceServletContextListener {

    @Override
    public Injector getInjector() {
        return Guice.createInjector(new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                serve("/first/*").with(FirstServlet.class);
                install(new SecondModule());
                install(new ChainedModule());
                serve("/*").with(AllServlet.class);
            }
        },
        new DefaultModule()
        );
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        super.contextInitialized(servletContextEvent);

        ServletContext servletContext = servletContextEvent.getServletContext();
        Injector injector = (Injector) servletContext.getAttribute(Injector.class.getName());

        if (System.getProperty("guice.version") == null ||
            System.getProperty("guice.version").equals("3.0")) {
            Util.guice3(injector);
        } else {
            Util.guice6(injector);
        }
    }
}

