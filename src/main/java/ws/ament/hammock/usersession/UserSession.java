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
