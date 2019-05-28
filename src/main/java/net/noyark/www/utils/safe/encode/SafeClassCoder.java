package net.noyark.www.utils.safe.encode;

import net.noyark.www.utils.api.ClassCoder;
import net.noyark.www.utils.encode.Util;


/**
 * 最新的更安全的加密对象，基于aes和base64加密
 */

public class SafeClassCoder extends ClassCoder {


    @Override
    public void encode(String fileName, String... classname) {
        try{
            AESGenerateKey.createKey(fileName);
            SafeEncryptClasses safeEncryptClasses = new SafeEncryptClasses();
            safeEncryptClasses.encode(fileName,classname);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Class<?> decode(String keyFile, String classname, boolean executeMain) {
        try{
            Class<?> thisClass = new SafeClassLoader(keyFile).loadClass(classname);
            if(executeMain) {
                thisClass.getClass().getDeclaredMethod("main").invoke(null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Class<?> decode(String keyFile, String classname) {
        return decode(keyFile,classname,false);
    }

    @Override
    public Class<?> getClassInJar(String jarFile, String classname, String keyFile) {
        return Util.getClassInJar(jarFile,classname,keyFile,DecryptMethod.AES);
    }

    @Override
    public Class<?> getClassInJar(String jarFile, String classname, String keyFile, ClassLoader loader) {
        return Util.getClassInJar(jarFile,classname,keyFile,loader,DecryptMethod.AES);
    }
}
