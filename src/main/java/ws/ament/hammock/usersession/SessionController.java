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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class SessionController {
    @Inject
    private SessionStore sessionStore;

    private static final ThreadLocal<UserSession> threadLocal = new ThreadLocal<>();
    public boolean isActive() {
        return true;
    }

    public UserSession createNewSession() {
        String sessionId = UUID.randomUUID().toString();
        final UserSession userSession = new UserSession(sessionId);
        threadLocal.set(userSession);
        return userSession;
    }
    public UserSession associate(String sessionId) {
        UserSession userSession = sessionStore.getUserSession(sessionId).orElse(new UserSession(sessionId));
        threadLocal.set(userSession);
        return userSession;
    }

    public static UserSession get() {
        return threadLocal.get();
    }

    public void disassociate() {
        threadLocal.remove();
    }
}
