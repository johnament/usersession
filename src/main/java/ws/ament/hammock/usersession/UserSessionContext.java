package ws.ament.hammock.usersession;

import javax.enterprise.context.spi.AlterableContext;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import java.lang.annotation.Annotation;

public class UserSessionContext implements AlterableContext {
    @Override
    public Class<? extends Annotation> getScope() {
        return UserSessionScoped.class;
    }

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        return null;
    }

    @Override
    public <T> T get(Contextual<T> contextual) {
        return null;
    }

    @Override
    public void destroy(Contextual<?> contextual) {

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
