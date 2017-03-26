package ws.ament.hammock.usersession.servlet;

import ws.ament.hammock.usersession.SessionController;
import ws.ament.hammock.usersession.UserSession;

import javax.enterprise.inject.Vetoed;
import javax.enterprise.inject.spi.CDI;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Vetoed
public class UserSessionFilterAssociator implements Filter {
    private String queryParam;
    private String headerParam;
    private SessionController sessionController;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.queryParam = filterConfig.getInitParameter("sessionIdParameterQueryParam");
        this.headerParam = filterConfig.getInitParameter("sessionIdParameterHeaderParam");
        this.sessionController = CDI.current().select(SessionController.class).get();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String sessionId = getSessionIdFromRequest((HttpServletRequest)servletRequest);
        UserSession userSession;
        if(sessionId != null) {
            userSession = sessionController.associate(sessionId);
        }
        else {
            userSession = sessionController.createNewSession();
            sessionId = userSession.getSessionId();
        }
        servletRequest = new HttpRequestWrapper((HttpServletRequest) servletRequest, userSession);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        finally {
            if(sessionId != null) {
                sessionController.disassociate();
                HttpServletResponse response = (HttpServletResponse)servletResponse;
                response.setHeader(headerParam, sessionId);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private String getSessionIdFromRequest(HttpServletRequest servletRequest) {
        final String sessionIdQueryParam = servletRequest.getParameter(queryParam);
        if(sessionIdQueryParam != null) {
            return sessionIdQueryParam;
        }
        return servletRequest.getHeader(headerParam);
    }
}
