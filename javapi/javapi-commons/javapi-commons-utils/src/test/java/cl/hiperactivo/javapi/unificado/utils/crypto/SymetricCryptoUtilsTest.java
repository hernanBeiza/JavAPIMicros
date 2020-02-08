package cl.hiperactivo.javapi.unificado.utils.crypto;

import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SymetricCryptoUtilsTest {

  private static SymetricCryptoUtils scu;

  @BeforeClass
  public static void before() {
    scu = new SymetricCryptoUtils();
  }

  @Test
  public void randomKeyEncryptDecrypt()
      throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
    String randomKey = scu.generateKey(128);
    String message = RandomStringUtils.randomAlphanumeric(4000);
    String encrypted = scu.encryptText(message, randomKey);
    String decrypted = scu.decrypText(encrypted, randomKey);
    Assert.assertEquals(message, decrypted);
  }

  @Test
  public void randomKeyGeneration() {
    String randomKey1 = scu.generateKey(128);
    String randomKey2 = scu.generateKey(128);
    Assert.assertNotEquals(randomKey1, randomKey2);
  }
}
