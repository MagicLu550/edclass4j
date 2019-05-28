package net.noyark.www.utils.encode;

import net.noyark.www.utils.Message;
import net.noyark.www.utils.api.ClassCoder;
import net.noyark.www.utils.ex.ParseException;

/**
 * 将加密方法集成在这里
 * classpath在配置文件指定
 */

public class SimpleClassCoder extends ClassCoder {



    public void encode(String fileName,String... classname){
        try{
            //create key
            GenerateKey.createKey(new String[]{fileName});
            EncryptClasses.encode(fileName,classname);
            Message.info("create key file "+fileName);
        }catch (Exception e){
            throw new ParseException("the class have some problems..",e);
        }
    }

    public Class<?> decode(String keyFile,String classname,boolean executeMain){
        try{
            return DecryptStart.decode(new String[]{keyFile,classname},executeMain);
        }catch (Exception e){
            e.printStackTrace();
            throw new ParseException("the class have some problems..",e);
        }
    }
    //不执行main
    public Class<?> decode(String keyFile,String classname){
        return decode(keyFile,classname,false);
    }

    @Override
    public Class<?> getClassInJar(String jarFile, String classname, String keyFile) {
        return Util.getClassInJar(jarFile,classname,keyFile,DecryptMethod.DES);
    }

    @Override
    public Class<?> getClassInJar(String jarFile, String classname, String keyFile, ClassLoader loader) {
        return Util.getClassInJar(jarFile,classname,keyFile,loader,DecryptMethod.DES);
    }
}

