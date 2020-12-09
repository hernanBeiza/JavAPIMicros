package cl.hiperactivo.javapi.unificado.test.security;

import cl.hiperactivo.javapi.unificado.utils.security.UnificadoPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockCustomUserSecurityContextFactory implements
    WithSecurityContextFactory<MockUser> {

  @Override
  public SecurityContext createSecurityContext(MockUser mockUser) {
    SecurityContext context = SecurityContextHolder.createEmptyContext();

    UnificadoPrincipal principal =
        new UnificadoPrincipal();
    principal.setAccessLevel(mockUser.accessLevel());
    principal.setEntityId(mockUser.entityId());
    principal.setClientId(mockUser.clientId());
    principal.setUsername(mockUser.username());
    principal.setId(mockUser.id());
    Authentication auth =
        new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
    context.setAuthentication(auth);
    return context;
  }
}
