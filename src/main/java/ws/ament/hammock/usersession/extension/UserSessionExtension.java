package ws.ament.hammock.usersession.extension;

import ws.ament.hammock.usersession.UserSessionContext;

import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

public class UserSessionExtension implements Extension{
    public void addUserSessionContext(AfterBeanDiscovery afterBeanDiscovery) {
        afterBeanDiscovery.addContext(new UserSessionContext());
    }
}
