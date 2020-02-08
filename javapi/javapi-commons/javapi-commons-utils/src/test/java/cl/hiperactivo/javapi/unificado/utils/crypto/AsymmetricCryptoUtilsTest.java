package cl.hiperactivo.javapi.unificado.utils.crypto;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AsymmetricCryptoUtilsTest {

  static File privateKeyFile;
  static File publicKeyFile;
  static String privateKeyBase64;
  static String publicKeyBase64;
  static private PrivateKey privateKey;
  static private PublicKey publicKey;
  static private String message;

  @BeforeClass
  static public void before() throws Exception {
    message = RandomStringUtils.random(4000);
    GenerateKeys gk = new GenerateKeys(2048);
    gk.createKeys();
    privateKey = gk.getPrivateKey();
    publicKey = gk.getPublicKey();
    privateKeyBase64 = gk.getPrivateKeyAsBase64();
    publicKeyBase64 = gk.getPublicKeyAsBase64();
    System.out.println("PRIVATE:\n" + privateKeyBase64);
    System.out.println("PUBLIC:\n" + publicKeyBase64);
    privateKeyFile = File.createTempFile("private", ".key");
    publicKeyFile = File.createTempFile("public", ".key");
    gk.writePrivateKeyToFile(privateKeyFile.getPath());
    gk.writePublicKeyToFile(publicKeyFile.getPath());
  }

  @AfterClass
  static public void after() {
    privateKeyFile.delete();
    publicKeyFile.delete();
  }

  @Test
  public void encryptWithPrivate()
      throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
    AsymmetricCryptoUtils acu = new AsymmetricCryptoUtils();
    String encrypted = acu.encryptText(message, privateKey);
    String decrypted = acu.decryptText(encrypted, publicKey);
    Assert.assertEquals(decrypted, message);
  }

  @Test
  public void encryptWithPublic()
      throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
    AsymmetricCryptoUtils acu = new AsymmetricCryptoUtils();
    String encrypted = acu.encryptText(message, publicKey);
    String decrypted = acu.decryptText(encrypted, privateKey);
    Assert.assertEquals(decrypted, message);
  }

  @Test
  public void failOnSymetricDecription()
      throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, UnsupportedEncodingException {
    AsymmetricCryptoUtils acu = new AsymmetricCryptoUtils();
    CryptoException e = null;
    try {
      String encrypted = acu.encryptText(message, privateKey);
      acu.decryptText(encrypted, privateKey);
    } catch (CryptoException ee) {
      e = ee;
    }
    Assert.assertNotNull(e);
    e = null;
    try {
      String encrypted = acu.encryptText(message, publicKey);
      acu.decryptText(encrypted, publicKey);
    } catch (CryptoException ee) {
      e = ee;
    }
    Assert.assertNotNull(e);
  }

  @Test
  public void readFromFile() throws Exception {
    AsymmetricCryptoUtils acu = new AsymmetricCryptoUtils();
    PrivateKey privateKey = acu.getPrivate(privateKeyFile.getPath());
    PublicKey publicKey = acu.getPublic(publicKeyFile.getPath());
    String encrypted = acu.encryptText(message, privateKey);
    String decrypted = acu.decryptText(encrypted, publicKey);
    Assert.assertEquals(decrypted, message);
  }

  @Test
  public void readFromBase64() throws Exception {
    AsymmetricCryptoUtils acu = new AsymmetricCryptoUtils();
    PrivateKey privateKey = acu.getPrivateFromBase64(privateKeyBase64);
    PublicKey publicKey = acu.getPublicFromBase64(publicKeyBase64);
    String encrypted = acu.encryptText(message, privateKey);
    String decrypted = acu.decryptText(encrypted, publicKey);
    Assert.assertEquals(decrypted, message);
  }


}
