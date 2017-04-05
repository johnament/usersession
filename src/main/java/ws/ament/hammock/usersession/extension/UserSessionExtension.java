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

package ws.ament.hammock.usersession.extension;

import ws.ament.hammock.usersession.UserSessionContext;
import ws.ament.hammock.usersession.UserSessionScoped;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

public class UserSessionExtension implements Extension{
    public void addScope(@Observes BeforeBeanDiscovery beforeBeanDiscovery) {
        beforeBeanDiscovery.addScope(UserSessionScoped.class, true, true);
    }
    public void addUserSessionContext(@Observes AfterBeanDiscovery afterBeanDiscovery) {
        afterBeanDiscovery.addContext(new UserSessionContext());
    }
}
