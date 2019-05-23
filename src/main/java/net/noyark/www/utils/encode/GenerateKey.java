package net.noyark.www.utils.encode;

import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class GenerateKey
{
    static public void createKey(String args[]) throws Exception {
        String keyFilename = args[0];
        String algorithm = "DES";

        // 生成密匙
        SecureRandom sr = new SecureRandom();
        KeyGenerator kg = KeyGenerator.getInstance(algorithm);
        kg.init(sr);
        SecretKey key = kg.generateKey();

        // 把密匙数据保存到文件
        Util.writeFile(keyFilename, key.getEncoded());
    }
}
