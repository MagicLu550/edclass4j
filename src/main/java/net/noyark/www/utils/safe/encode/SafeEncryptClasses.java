package net.noyark.www.utils.safe.encode;

import net.noyark.www.utils.Message;
import net.noyark.www.utils.encode.Util;

/**
 * 更加安全的加密方式
 */

public class SafeEncryptClasses {

    public void encode(String keyFile,String... classnames){
        try{
            String key = CodeUtils.readKey(keyFile);
            for(String classname:classnames){
                String filename = Util.getClassPath(classname);
                byte[] classData = Util.readFile(filename+".class");
                byte[] aesData = CodeUtils.aesDecrypt(classData,key);
                Util.writeClassData(filename,aesData);
                Message.info("Encrypted " + filename);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
