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

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Set;
import java.util.Vector;

public class HttpSessionWrapper extends ForwardingHttpSession {
    private final UserSession userSession;
    public HttpSessionWrapper(HttpSession delegate, UserSession userSession) {
        super(delegate);
        this.userSession = userSession;
    }
    // this is what has to be stored in the user session
    @Override
    public Object getAttribute(String s) {
        return userSession.getAttribute(s);
    }

    @Override
    public Object getValue(String s) {
        return userSession.getValue(s);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return new Vector<>(userSession.getAttributeNames()).elements();
    }

    @Override
    public String[] getValueNames() {
        final Set<String> valueNames = userSession.getValueNames();
        return valueNames.toArray(new String[valueNames.size()]);
    }

    @Override
    public void setAttribute(String s, Object o) {
        userSession.setAttrbute(s, o);
    }

    @Override
    public void putValue(String s, Object o) {
        userSession.setValue(s,o);
    }

    @Override
    public void removeAttribute(String s) {
        userSession.setAttrbute(s, null);
    }

    @Override
    public void removeValue(String s) {
        userSession.setAttrbute(s, null);
    }
}
