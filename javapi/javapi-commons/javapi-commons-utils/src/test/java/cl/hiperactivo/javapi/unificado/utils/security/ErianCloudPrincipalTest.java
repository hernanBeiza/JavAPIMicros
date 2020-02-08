package cl.hiperactivo.javapi.unificado.utils.security;

import org.junit.Assert;
import org.junit.Test;

public class ErianCloudPrincipalTest {

  @Test
  public void basicTest() {
    UnificadoPrincipal ecp = new UnificadoPrincipal();
    ecp.setUsername("username");
    ecp.setClientId("clientId");
    ecp.setEntityId("entityId");
    ecp.setAuthToken("authtoken");
    ecp.setName("name");
    ecp.setRefreshToken("refreshtoken");
    ecp.setAccessLevel(UnificadoAccessLevel.CLOUD_USER);
    ecp.setId(1L);
    Assert.assertEquals("username", ecp.getUsername());
    Assert.assertEquals("clientId", ecp.getClientId());
    Assert.assertEquals("entityId", ecp.getEntityId());
    Assert.assertEquals("authtoken", ecp.getAuthToken());
    Assert.assertEquals("name", ecp.getName());
    Assert.assertEquals(1L, ecp.getId().longValue());
    Assert.assertEquals("refreshtoken", ecp.getRefreshToken());
    Assert.assertEquals(UnificadoAccessLevel.CLOUD_USER, ecp.getAccessLevel());
    Assert.assertEquals(1, ecp.getAuthorities().size());
    ecp.setAccessLevel(UnificadoAccessLevel.CLIENT_ADMIN);
    Assert.assertEquals(2, ecp.getAuthorities().size());
    ecp.setAccessLevel(UnificadoAccessLevel.CLOUD_ADMIN);
    Assert.assertEquals(3, ecp.getAuthorities().size());

  }
}
