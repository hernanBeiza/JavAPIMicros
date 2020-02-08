package cl.hiperactivo.javapi.unificado.utils.crypto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.apache.commons.codec.binary.Base64;

/**
 * Created by diego on 10/18/17.
 */
public class GenerateKeys {

    private KeyPairGenerator keyGen;
    private KeyPair pair;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public GenerateKeys(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keylength);
    }

    public void createKeys() {
        this.pair = this.keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }
    public String getPrivateKeyAsBase64() {
        return Base64.encodeBase64String(this.privateKey.getEncoded());
    }

    public String getPublicKeyAsBase64() {
        return Base64.encodeBase64String(this.publicKey.getEncoded());
    }

    public void writePublicKeyToFile(String path) throws IOException {
       this.writeToFile(path,getPublicKey().getEncoded());
    }
    public void writePrivateKeyToFile(String path) throws IOException {
        this.writeToFile(path,getPrivateKey().getEncoded());
    }

    private void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }
}