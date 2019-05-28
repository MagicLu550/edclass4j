package net.noyark.www.utils.encode;

import net.noyark.www.utils.Message;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class EncryptClasses
{
    static public void encode(String keyFilename,String args[]) throws Exception {
        String algorithm = "DES";

        // 生成密匙
        SecureRandom sr = new SecureRandom();
        byte rawKey[] = Util.readFile(Util.getKeyFile()+"/"+keyFilename);
        DESKeySpec dks = new DESKeySpec(rawKey);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( algorithm );
        SecretKey key = keyFactory.generateSecret(dks);

        // 创建用于实际加密操作的Cipher对象
        Cipher ecipher = Cipher.getInstance(algorithm);
        ecipher.init(Cipher.ENCRYPT_MODE, key, sr);

        // 加密命令行中指定的每一个类
        for (int i=0; i<args.length; ++i) {
            String filename = Util.getClassPath(args[i]);
            byte[] classData = Util.readFile(filename+".class");  //读入类文件
            byte[] encryptedClassData = ecipher.doFinal(classData);  //加密
            Util.writeClassData(filename,encryptedClassData);
            Message.info("Encrypted " + filename);
        }
    }
}
