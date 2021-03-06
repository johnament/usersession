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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

public abstract class ForwardingHttpSession implements HttpSession{
    protected final HttpSession delegate;

    public ForwardingHttpSession(HttpSession delegate) {
        this.delegate = delegate;
    }

    @Override
    public long getCreationTime() {
        return delegate.getCreationTime();
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public long getLastAccessedTime() {
        return delegate.getLastAccessedTime();
    }

    @Override
    public ServletContext getServletContext() {
        return delegate.getServletContext();
    }

    @Override
    public void setMaxInactiveInterval(int i) {
        delegate.setMaxInactiveInterval(i);
    }

    @Override
    public int getMaxInactiveInterval() {
        return delegate.getMaxInactiveInterval();
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return delegate.getSessionContext();
    }

    @Override
    public Object getAttribute(String s) {
        return delegate.getAttribute(s);
    }

    @Override
    public Object getValue(String s) {
        return delegate.getValue(s);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return delegate.getAttributeNames();
    }

    @Override
    public String[] getValueNames() {
        return delegate.getValueNames();
    }

    @Override
    public void setAttribute(String s, Object o) {
        delegate.setAttribute(s, o);
    }

    @Override
    public void putValue(String s, Object o) {
        delegate.putValue(s, o);
    }

    @Override
    public void removeAttribute(String s) {
        delegate.removeAttribute(s);
    }

    @Override
    public void removeValue(String s) {
        delegate.removeValue(s);
    }

    @Override
    public void invalidate() {
        delegate.invalidate();
    }

    @Override
    public boolean isNew() {
        return delegate.isNew();
    }
}
