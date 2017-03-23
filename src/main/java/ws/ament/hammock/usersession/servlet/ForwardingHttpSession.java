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
