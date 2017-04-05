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

package ws.ament.hammock.usersession;

import javax.enterprise.context.spi.AlterableContext;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.CDI;
import java.lang.annotation.Annotation;

public class UserSessionContext implements AlterableContext {
    @Override
    public Class<? extends Annotation> getScope() {
        return UserSessionScoped.class;
    }

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        Class<?> beanClass = ((Bean) contextual).getBeanClass();
        UserSession userSession = SessionController.get();
        UserSessionInstance<T> attribute = userSession.getAttribute(beanClass.getName());
        if(attribute == null) {
            attribute = UserSessionInstance.create(contextual, creationalContext);
            userSession.setAttrbute(beanClass.getName(), attribute);
        }
        return attribute.getInstance();
    }

    @Override
    public <T> T get(Contextual<T> contextual) {
        CreationalContext<T> creationalContext = CDI.current().getBeanManager().createCreationalContext(contextual);
        return get(contextual, creationalContext);
    }

    @Override
    public void destroy(Contextual<?> contextual) {
        Class<?> beanClass = ((Bean) contextual).getBeanClass();
        UserSession userSession = SessionController.get();
        UserSessionInstance<?> attribute = userSession.getAttribute(beanClass.getName());
        attribute.destroy();
        userSession.setAttrbute(beanClass.getName(), null);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
