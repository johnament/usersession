/*
 * Copyright 2017 Hammock and its contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ws.ament.hammock.usersession.servlet;

import ws.ament.hammock.usersession.SessionController;
import ws.ament.hammock.usersession.SessionStore;
import ws.ament.hammock.usersession.UserSession;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Dependent
@WebFilter(urlPatterns = {"/*"},filterName = "UserSessionFilter")
public class UserSessionFilterAssociator implements Filter {

    public static final String HEADER_PARAM = "headerParam";
    public static final String QUERY_PARAM = "queryParam";

    @Inject
    private SessionController sessionController;

    @Inject
    private SessionStore sessionStore;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
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
        sessionStore.save(userSession);
        servletRequest = new HttpRequestWrapper((HttpServletRequest) servletRequest, userSession);
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.addHeader(HEADER_PARAM, sessionId);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        finally {
            if(sessionId != null) {
                sessionController.disassociate();
            }
        }
    }

    @Override
    public void destroy() {

    }

    private String getSessionIdFromRequest(HttpServletRequest servletRequest) {
        final String sessionIdQueryParam = servletRequest.getParameter(QUERY_PARAM);
        if(sessionIdQueryParam != null) {
            return sessionIdQueryParam;
        }
        return servletRequest.getHeader(HEADER_PARAM);
    }
}
