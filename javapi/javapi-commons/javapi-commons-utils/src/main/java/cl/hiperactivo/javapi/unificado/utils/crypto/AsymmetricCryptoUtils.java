package cl.hiperactivo.javapi.unificado.utils.crypto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 * Clase de utilidades de cripto asimetricas
 */
public class AsymmetricCryptoUtils {

  private Cipher cipher;

  private SymetricCryptoUtils scu;

  /**
   * Constructor
   */
  public AsymmetricCryptoUtils() {

    try {
      this.cipher = Cipher.getInstance("RSA");
    } catch (Exception e) {
      throw new CryptoException(e);
    }
    this.scu = new SymetricCryptoUtils();

  }

  /**
   * Metodo de obtencion de llave privada
   *
   * @param filename ruta del archivo
   * @return llave privada
   */
  public PrivateKey getPrivate(String filename) {
    try {
      byte[] keyBytes = readAllBytes(filename);
      PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
      KeyFactory kf = KeyFactory.getInstance("RSA");
      return kf.generatePrivate(spec);
    } catch (Exception e) {
      throw new CryptoException(e);
    }
  }

  /**
   * Metodo para obtencion de llave publica desde string en base64
   *
   * @param base64 llave en base64
   * @return LLave 64
   * @throws NoSuchAlgorithmException Error
   * @throws InvalidKeySpecException Error
   */
  public PublicKey getPublicFromBase64(String base64) {
    try {
      byte[] keyBytes = Base64.decodeBase64(base64);
      X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
      KeyFactory kf = KeyFactory.getInstance("RSA");
      return kf.generatePublic(spec);
    } catch (Exception e) {
      throw new CryptoException(e);
    }
  }

  /**
   * Metodo para obtener llave privada
   *
   * @param base64 Llave privada en base64
   * @return llave privada
   */
  public PrivateKey getPrivateFromBase64(String base64) {
    byte[] keyBytes = Base64.decodeBase64(base64);
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
    try {
      KeyFactory kf = KeyFactory.getInstance("RSA");
      return kf.generatePrivate(spec);
    } catch (Exception e) {
      throw new CryptoException(e);
    }
  }

  /**
   * Metodo para obtener llave publica
   *
   * @param filename ruta de archivo
   * @return llave publica
   */
  public PublicKey getPublic(String filename) {
    try {
      byte[] keyBytes = readAllBytes(filename);
      X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
      KeyFactory kf = KeyFactory.getInstance("RSA");
      return kf.generatePublic(spec);
    } catch (Exception e) {
      throw new CryptoException(e);
    }
  }

  private byte[] readAllBytes(String filename) throws IOException {
    return IOUtils.toByteArray(new File(filename).toURI());
  }

  /**
   * Metodo para encriptar un mensaje con una llave
   *
   * @param message a encriptar
   * @param key llave con la cual se encriptara
   * @return mensaje ecriptado
   */
  public String encryptText(String message, Key key) {
    String aesKey = scu.generateKey(128);
    try {
      String encryptedMessage = scu.encryptText(message, aesKey);
      String encryptedKey = Base64.encodeBase64String(
          this.applyKey(aesKey.getBytes(StandardCharsets.UTF_8), key, Cipher.ENCRYPT_MODE));
      return new StringBuilder().append(encryptedMessage).append('-').append(encryptedKey)
          .toString();
    } catch (Exception e) {
      throw new CryptoException(e);
    }
  }

  /**
   * Metodo para obtener metodo desencriptado con una llave
   *
   * @param encrypted mensaje encriptado
   * @param key llave con la cual se desencriptara
   * @return mensaje desencriptado
   */
  public String decryptText(String encrypted, Key key) {
    String[] parts = encrypted.split("-");
    if (parts.length != 2) {
      throw new CryptoException("Malformed message. mixed message is expected");
    }
    String encryptedMessage = parts[0];
    String encryptedKey = parts[1];
    try {
      String aesKey = new String(
          this.applyKey(Base64.decodeBase64(encryptedKey), key, Cipher.DECRYPT_MODE),
          StandardCharsets.UTF_8);
      return scu.decrypText(encryptedMessage, aesKey);
    } catch (Exception e) {
      throw new CryptoException(e);
    }
  }

  private byte[] applyKey(byte[] bytes, Key key, int mode)
      throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    this.cipher.init(mode, key);
    return cipher.doFinal(bytes);


  }

}
