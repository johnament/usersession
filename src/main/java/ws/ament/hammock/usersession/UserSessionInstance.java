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

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import java.io.Serializable;

public class UserSessionInstance<T> implements Serializable{
    private final Contextual<T> contextual;
    private final CreationalContext<T> creationalContext;
    private final T instance;

    public static <T> UserSessionInstance<T> create(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        T instance = contextual.create(creationalContext);
        return new UserSessionInstance<T>(contextual, creationalContext, instance);
    }

    private UserSessionInstance(Contextual<T> contextual, CreationalContext<T> creationalContext, T instance) {
        this.contextual = contextual;
        this.creationalContext = creationalContext;
        this.instance = instance;
    }

    public Contextual<T> getContextual() {
        return contextual;
    }

    public CreationalContext<T> getCreationalContext() {
        return creationalContext;
    }

    public T getInstance() {
        return instance;
    }

    public void destroy() {
        contextual.destroy(instance,creationalContext);
    }
}
