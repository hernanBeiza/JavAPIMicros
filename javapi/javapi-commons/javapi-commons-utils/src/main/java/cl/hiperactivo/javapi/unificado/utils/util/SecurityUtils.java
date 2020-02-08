package cl.hiperactivo.javapi.unificado.utils.util;

import cl.hiperactivo.javapi.unificado.utils.security.UnificadoAccessLevel;
import cl.hiperactivo.javapi.unificado.utils.security.UnificadoPrincipal;
import java.security.Principal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

  private SecurityUtils() {
  }

  public static UnificadoPrincipal getPrincipal() {
    Principal principal = SecurityContextHolder.getContext().getAuthentication();
    if (principal == null) {
      return null;
    }
    if (principal instanceof UsernamePasswordAuthenticationToken
        && ((UsernamePasswordAuthenticationToken) principal).getPrincipal() != null
        && ((UsernamePasswordAuthenticationToken) principal)
        .getPrincipal() instanceof UnificadoPrincipal) {
      return (UnificadoPrincipal) (((UsernamePasswordAuthenticationToken) principal)
          .getPrincipal());
    }
    return null;
  }

  public static Long getNormalizedTimestamp() {
    return System.currentTimeMillis() / 60000L;
  }

  public static String getEntityAuthorization() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth instanceof UsernamePasswordAuthenticationToken) {
      return auth.getCredentials().toString();
    } else {
      return null;
    }
  }

  public static Boolean checkAdministrationPrivileges(String clientId, String entityId) {
    UnificadoPrincipal principal = SecurityUtils.getPrincipal();
    if (principal != null) {
      switch (principal.getAccessLevel()) {
        case CLOUD_ADMIN:
          return true;
        case CLIENT_ADMIN:
          return clientId.equals(principal.getClientId()) && (
              entityId.equals(principal.getEntityId())
                  || principal.getEntityId().equals("admin"));
        default:
          return false;
      }
    }
    return false;
  }

  public static Boolean checkAccessPrivileges(String clientId, String entityId) {
    UnificadoPrincipal principal = SecurityUtils.getPrincipal();
    if (principal == null) {
      return false;
    }
    boolean adminPriv = checkAdministrationPrivileges(clientId, entityId);
    return adminPriv || (principal.getAccessLevel() == UnificadoAccessLevel.CLOUD_USER && principal
        .getClientId().equals(clientId) && principal.getEntityId().equals(entityId));
  }
}
