package ws.ament.hammock.usersession;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserSession {
    private final String id;
    private final Map<String, Object> values = new HashMap<>();
    private final Map<String, Object> attributes = new HashMap<>();

    public UserSession(String id) {
        this.id = id;
    }

    public <T> T getValue(String name) {
        return (T)values.get(name);
    }

    public <T> T getAttribute(String name) {
        return (T)attributes.get(name);
    }

    public Set<String> getAttributeNames() {
        return Collections.unmodifiableSet(attributes.keySet());
    }

    public Set<String> getValueNames() {
        return Collections.unmodifiableSet(values.keySet());
    }

    public void setAttrbute(String s, Object o) {
        attributes.put(s,o);
    }

    public void setValue(String s, Object o) {
        values.put(s,o);
    }

    public String getSessionId() {
        return id;
    }
}
