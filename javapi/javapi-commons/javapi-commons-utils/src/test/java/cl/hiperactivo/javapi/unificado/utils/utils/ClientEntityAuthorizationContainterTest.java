package cl.hiperactivo.javapi.unificado.utils.utils;

import cl.hiperactivo.javapi.unificado.utils.util.ClientEntityAuthorizationContainter;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

public class ClientEntityAuthorizationContainterTest {

  @Test
  public void registerGetTest() {
    String entity = "entity";
    String entity2 = "entity2";
    String pk = "pk";
    ClientEntityAuthorizationContainter containter = new ClientEntityAuthorizationContainter();
    Assert.assertNull(pk, containter.find(entity, Optional.empty()));
    containter.register(entity, pk);
    Assert.assertEquals(containter.find(entity, Optional.empty()), pk);
    containter.register(entity, "dummy");
    Assert.assertEquals(containter.find(entity2, Optional.of(() -> pk)), pk);
    Assert.assertEquals(containter.find(entity2, Optional.empty()), pk);
  }

  @Test
  public void cleanupTest() throws InterruptedException {
    ClientEntityAuthorizationContainter container = new ClientEntityAuthorizationContainter();
    container.setMaxSize(2);
    container.setMaxIdleTime(1000L);
    container.register("e1", "e1");
    container.register("e2", "e2");
    container.register("e3", "e3");
    Thread.sleep(1000L);
    container.find("e1", Optional.empty());
    container.find("e2", Optional.empty());
    container.register("e4", "e4");
    container.cleanup();
    Assert.assertNull(container.find("e3", Optional.empty()));
    Assert.assertNull(container.find("e1", Optional.empty()));
    Assert.assertEquals(container.find("e2", Optional.empty()), "e2");
    Assert.assertEquals(container.find("e4", Optional.empty()), "e4");
    Thread.sleep(1000L);
    container.find("e4", Optional.empty());
    Assert.assertNull(container.find("e1", Optional.empty()));
    Assert.assertEquals(container.find("e4", Optional.empty()), "e4");
    container.empty();
    Assert.assertNull(container.find("e4", Optional.empty()));


  }

}
