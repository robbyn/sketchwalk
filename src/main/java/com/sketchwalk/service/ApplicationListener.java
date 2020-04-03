package com.sketchwalk.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {
    private static final String ATTR_APPLICATION = "application";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(ATTR_APPLICATION, new Application());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute(ATTR_APPLICATION);
    }
}
