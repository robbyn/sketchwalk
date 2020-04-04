package com.sketchwalk.web;

import com.sketchwalk.service.Application;
import com.sketchwalk.service.SessionHelper;
import com.sketchwalk.web.ApplicationListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private static final String ATTR_SESSION = "com.sketchwalk.session";

    public static SessionHelper getSessionHelper(HttpSession httpSess) {
        return (SessionHelper)httpSess.getAttribute(ATTR_SESSION);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession httpSess = se.getSession();
        Application app = ApplicationListener.getApplication(
                httpSess.getServletContext());
        httpSess.setAttribute(ATTR_SESSION, app.openSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession httpSess = se.getSession();
        SessionHelper helper = getSessionHelper(httpSess);
        httpSess.removeAttribute(ATTR_SESSION);
        if (helper != null) {
            helper.close();
        }
    }
}
