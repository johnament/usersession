package ws.ament.hammock.usersession;

import javax.enterprise.context.spi.Contextual;
import java.io.Serializable;

public class UserSessionInstance<T> implements Serializable{
    private final Contextual<T> contextual;

    public UserSessionInstance(Contextual<T> contextual) {
        this.contextual = contextual;
    }
}
