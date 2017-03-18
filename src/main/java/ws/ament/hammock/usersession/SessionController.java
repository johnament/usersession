package ws.ament.hammock.usersession;

/**
 * Created by johnament on 3/14/17.
 */
public class SessionController {
    private static final ThreadLocal<UserSession> threadLocal = ThreadLocal.withInitial(UserSession::new);
    public boolean isActive() {
        return true;
    }
}
