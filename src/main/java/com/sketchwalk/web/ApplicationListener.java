package com.sketchwalk.web;

import com.sketchwalk.service.Application;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {
    private static final String ATTR_APPLICATION = "com.sketchwalk.application";

    public static Application getApplication(ServletContext context) {
        return (Application) context.getAttribute(ATTR_APPLICATION);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute(ATTR_APPLICATION, new Application());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Application app = getApplication(context);
        if (app != null) {
            context.removeAttribute(ATTR_APPLICATION);
            app.close();
        }
    }
}
