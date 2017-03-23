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
