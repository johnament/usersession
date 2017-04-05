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
