package com.sketchwalk.web;

import com.sketchwalk.service.RequestHelper;
import com.sketchwalk.service.SessionHelper;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {
    private static final String ATTR_TRANSACTION = "com.sketchwalk.transaction";

    public static RequestHelper getRequestHelper(ServletRequest req) {
        return (RequestHelper) req.getAttribute(ATTR_TRANSACTION);
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest)sre.getServletRequest();
        SessionHelper sess = SessionListener.getSessionHelper(
                req.getSession(true));
        String username = req.getRemoteUser();
        if (username == null) {
            username = "anonymous";
        }
        req.setAttribute(ATTR_TRANSACTION, sess.openRequest(username));
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest req = sre.getServletRequest();
        RequestHelper helper = (RequestHelper) req.getAttribute(ATTR_TRANSACTION);
        if (helper != null) {
            req.removeAttribute(ATTR_TRANSACTION);
            helper.close();
        }
    }
}
