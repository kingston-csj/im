import org.springframework.core.io.FileSystemResource;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.interfaces.RSAPublicKey;

public class Main {

    public static void main(String[] args) {

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new FileSystemResource("mykeystore.jks"), "123456".toCharArray());
        RSAPublicKey publicKey = (RSAPublicKey)keyStoreKeyFactory.getKeyPair("im").getPublic();
        String key = "-----BEGIN PUBLIC KEY-----\n" + new String(Base64.encode(publicKey.getEncoded()))
                + "\n-----END PUBLIC KEY-----";
        System.out.println(key);
    }
}
