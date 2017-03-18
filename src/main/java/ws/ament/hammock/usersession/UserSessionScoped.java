package ws.ament.hammock.usersession;

import javax.enterprise.context.NormalScope;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@NormalScope(passivating = true)
public @interface UserSessionScoped {
}
