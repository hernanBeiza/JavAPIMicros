package cl.hiperactivo.javapi.unificado.utils.crypto;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

public class GenerateKeysTest {

  @Test
  public void keyGenerationTest() throws NoSuchProviderException, NoSuchAlgorithmException {
    GenerateKeys gk = new GenerateKeys(2048);
    gk.createKeys();
    byte[] privateKey = gk.getPrivateKey().getEncoded();
    byte[] publicKey = gk.getPublicKey().getEncoded();
    String privateKeyBase64 = gk.getPrivateKeyAsBase64();
    String publicKeyBase64 = gk.getPublicKeyAsBase64();
    Assert.assertNotEquals(privateKeyBase64, publicKeyBase64);
    Assert.assertTrue(privateKey.length > publicKey.length);
    Assert.assertEquals(Base64.encodeBase64String(privateKey), privateKeyBase64);
    Assert.assertEquals(Base64.encodeBase64String(publicKey), publicKeyBase64);
  }

  @Test
  public void keyToFileTest() throws Exception {
    GenerateKeys gk = new GenerateKeys(2048);
    gk.createKeys();
    String privateKey = gk.getPrivateKeyAsBase64();
    String publicKey = gk.getPublicKeyAsBase64();
    File privateKeyFile = File.createTempFile("private", ".key");
    File publicKeyFile = File.createTempFile("public", ".key");
    gk.writePrivateKeyToFile(privateKeyFile.getPath());
    gk.writePublicKeyToFile(publicKeyFile.getPath());
    byte[] privateBytes = IOUtils.toByteArray(privateKeyFile.toURI());
    byte[] publicBytes = IOUtils.toByteArray(publicKeyFile.toURI());
    Assert.assertEquals(Base64.encodeBase64String(privateBytes), privateKey);
    Assert.assertEquals(Base64.encodeBase64String(publicBytes), publicKey);
    privateKeyFile.delete();
    publicKeyFile.delete();


  }
}
