package net.noyark.www.utils.safe.encode;

/**
 * 生成key文件
 */
public class AESGenerateKey {

    static public void createKey(String keyFile) throws Exception{
        CodeUtils.randomKey(keyFile);
    }

}
