package cl.hiperactivo.javapi.unificado.utils.security;

import cl.hiperactivo.javapi.unificado.utils.util.JSONHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.server.ResponseStatusException;

public class UnificadoPrincipal extends UnificadoUserData implements AuthenticatedPrincipal,
    Serializable, Principal {

  protected static final long serialVersionUID = 3907555093679541581L;
  private static final Log logger = LogFactory.getLog(UnificadoPrincipal.class);
  protected String authToken;
  protected String refreshToken;

  public UnificadoPrincipal() {
  }

  public UnificadoPrincipal(UnificadoUserData userData) {
    try {
      BeanUtils.copyProperties(this, userData);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  @Override
  public String toString() {
    return JSONHelper.toJSON(this);
  }

  @JsonIgnore
  public List<GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(this.getAccessLevel().getAsRole()));
    if (this.getAccessLevel() == UnificadoAccessLevel.CLOUD_ADMIN) {
      authorities.add(new SimpleGrantedAuthority(UnificadoAccessLevel.CLIENT_ADMIN.getAsRole()));
    }
    if (this.getAccessLevel() != UnificadoAccessLevel.CLOUD_USER) {
      authorities.add(new SimpleGrantedAuthority(UnificadoAccessLevel.CLOUD_USER.getAsRole()));

    }
    authorities.addAll(this.getGroups().stream().map(SimpleGrantedAuthority::new).collect(
        Collectors.toList()));
    return authorities;
  }
}
