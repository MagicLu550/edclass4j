package net.noyark.www.utils.encode;

import net.noyark.www.utils.Message;
import net.noyark.www.utils.ex.ParseException;

public class SimpleClassCoder {

    private static SimpleClassCoder classCoder;

    static {
        classCoder = new SimpleClassCoder();
    }

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

    public void decode(String classname,String classpath,String keyFile){

    }

    public static SimpleClassCoder getClassCoder() {
        return classCoder;
    }
}
