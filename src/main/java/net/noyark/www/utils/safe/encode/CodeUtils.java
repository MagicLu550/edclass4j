package net.noyark.www.utils.safe.encode;

import net.noyark.www.utils.encode.Util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Base64;

/**
 * 对于aes加密的调优解决方案
 * 未来将采用最新的解决方案
 */

public class CodeUtils {

    /**
     * 密钥
     */
    private static final String KEY = "1234567887654321";// AES加密要求key必须要128个比特位（这里需要长度为16，否则会报错）

    public static String randomKey(String fileName) throws IOException {
        String randomKey = (System.currentTimeMillis()+""+(((fileName.hashCode()&Integer.MAX_VALUE)%25)+100)+"");
        if(randomKey.length()>16){
            randomKey = randomKey.substring(0,16);
        }
        Util.writeFile(fileName,randomKey);
        return randomKey;
    }

    public static String readKey(String fileName) throws IOException{
        return Util.readKeyFile(fileName);
    }

    /**
     * 算法
     */
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    public static void main(String[] args) throws Exception {
        byte[] content = "url：findNames你好.action".getBytes();
        System.out.println("加密前：" + new String(content));

        String key =randomKey("hello.key");

        System.out.println("加密密钥和解密密钥：" + key);
        System.out.println();
        byte[] encrypt = aesEncrypt(content, readKey("hello.key"));
        System.out.println("加密后：" + new String(encrypt));

        byte[] decrypt = aesDecrypt(encrypt, readKey("hello.key"));

        System.out.println("解密后：" + new String(decrypt));
    }

    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    private static byte[] base64Encode(byte[] bytes){
        return Base64.getEncoder().encode(bytes);
    }

    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception 抛出异常
     */
    private static byte[] base64Decode(byte[] base64Code) throws Exception{
        return Base64.getDecoder().decode(base64Code);
    }


    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     */
    private static byte[] aesEncryptToBytes(byte[] content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content);
    }


    /**
     * AES加密为base 64 code
     *
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     */
    public static byte[] aesEncrypt(byte[] content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     */
    private static byte[] aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        return cipher.doFinal(encryptBytes);
    }


    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     */
    public static byte[] aesDecrypt(byte[] encryptStr, String decryptKey) throws Exception {
        return aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

}
