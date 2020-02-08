package cl.hiperactivo.javapi.unificado.utils.crypto;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class SymetricCryptoUtils {
    private Cipher cipher;

    public SymetricCryptoUtils() {
        try {
            cipher = Cipher.getInstance("AES");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] decryptMessage(byte[] encrypted, String base64Key) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return this.applyKey(encrypted, base64Key, Cipher.DECRYPT_MODE);
    }

    private byte[] encryptMessage(byte[] message, String base64Key) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        return this.applyKey(message, base64Key, Cipher.ENCRYPT_MODE);
    }

    private byte[] applyKey(byte[] message, String base64Key, int mode) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        cipher.init(mode, tranformarKey(Base64.decodeBase64(base64Key)));
        return cipher.doFinal(message);
    }

    public String encryptText(String message, String base64Key) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        return Base64.encodeBase64String(this.encryptMessage(message.getBytes(StandardCharsets.UTF_8), base64Key));
    }

    public String decrypText(String encrypted, String base64Key) throws BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        byte[] message = Base64.decodeBase64(encrypted);
        return new String(this.decryptMessage(message, base64Key), StandardCharsets.UTF_8);
    }


    private SecretKey tranformarKey(byte[] key) {
        SecretKey serverKeyFile = new SecretKeySpec(key, "AES");
        return serverKeyFile;
    }

    public String generateKey(int keylen) {
        KeyGenerator keygen = null;
        try {
            keygen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keygen.init(keylen);
        return Base64.encodeBase64String(keygen.generateKey().getEncoded());
    }
}