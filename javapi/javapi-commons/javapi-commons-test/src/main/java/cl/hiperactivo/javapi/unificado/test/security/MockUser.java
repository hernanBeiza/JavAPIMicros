package cl.hiperactivo.javapi.unificado.test.security;

import cl.hiperactivo.javapi.unificado.utils.security.UnificadoAccessLevel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface MockUser {

  String username() default "ec_user";

  String clientId() default "erian-cloud";

  String entityId() default "admin";

  long id() default 1L;

  UnificadoAccessLevel accessLevel() default UnificadoAccessLevel.CLIENT_ADMIN;
}
