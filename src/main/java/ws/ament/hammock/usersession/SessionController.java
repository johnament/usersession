package ws.ament.hammock.usersession;

import java.util.UUID;

/**
 * Created by johnament on 3/14/17.
 */
public class SessionController {
    private static final ThreadLocal<UserSession> threadLocal = new ThreadLocal<>();
    public boolean isActive() {
        return true;
    }

    public UserSession createNewSession() {
        String id = UUID.randomUUID().toString();
        final UserSession userSession = new UserSession(id);
        threadLocal.set(userSession);
        return userSession;
    }
    public UserSession associate(String sessionId) {
        UserSession userSession = new UserSession(sessionId);
        threadLocal.set(userSession);
        return userSession;
    }

    public void disassociate() {
        threadLocal.remove();
    }
}
