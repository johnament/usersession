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
        return contextual.create(creationalContext);
    }

    @Override
    public <T> T get(Contextual<T> contextual) {
        // need a way to convert a contextual into a string without losing the data
        // the object stored within the user session has to contain all of this data.
        return get(contextual, null);
    }

    @Override
    public void destroy(Contextual<?> contextual) {
//        contextual.destroy();
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
