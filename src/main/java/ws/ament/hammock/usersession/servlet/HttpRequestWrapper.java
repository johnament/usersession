package ws.ament.hammock.usersession.servlet;

import ws.ament.hammock.usersession.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class HttpRequestWrapper extends HttpServletRequestWrapper {
    private final HttpSessionWrapper httpSessionWrapper;
    public HttpRequestWrapper(HttpServletRequest servletRequest, UserSession userSession) {
        super(servletRequest);
        this.httpSessionWrapper = new HttpSessionWrapper(super.getSession(),userSession);
    }

    @Override
    public HttpSession getSession() {
        return httpSessionWrapper;
    }

    @Override
    public HttpSession getSession(boolean b) {
        return httpSessionWrapper;
    }
}
