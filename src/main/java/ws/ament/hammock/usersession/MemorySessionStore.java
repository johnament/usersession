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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class MemorySessionStore implements SessionStore{
    private final Map<String, UserSession> userSessionMap = new HashMap<>();

    @Override
    public Optional<UserSession> getUserSession(String sessionId) {
        return Optional.ofNullable(userSessionMap.get(sessionId));
    }

    @Override
    public void save(UserSession userSession) {
        userSessionMap.put(userSession.getSessionId(), userSession);
    }

    @Override
    public void delete(UserSession userSession) {
        userSessionMap.remove(userSession.getSessionId());
    }
}
