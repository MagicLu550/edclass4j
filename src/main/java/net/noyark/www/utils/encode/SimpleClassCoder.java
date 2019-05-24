package net.noyark.www.utils.encode;

import net.noyark.www.utils.Message;
import net.noyark.www.utils.ReflectSet;
import net.noyark.www.utils.api.ClassCoder;
import net.noyark.www.utils.ex.ParseException;

import java.io.File;
import java.util.List;

/**
 * 将加密方法集成在这里
 * classpath在配置文件指定
 */

public class SimpleClassCoder implements ClassCoder {



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
    //加密整个class系统
    public List<Class<?>> recursiveEncode(String mainClass,String keyFile){
        return ReflectSet.getReflectSet().load(this,keyFile,false,mainClass);
    }
    //加密整个class系统
    public List<Class<?>> recursiveDecode(String mainClass,String keyFile){
        return ReflectSet.getReflectSet().load(this,keyFile,true,mainClass);
    }



    /** 设置读取classpath的路径，即包前面的根路径，如果不设置，默认为在项目资源文件 */

    public void setApplicationFile(String file){
       Util.setReadApplication(file);
    }
    /** 获取class文件输出路径，如果为this，则是覆盖现行的路径 */
    public String  getClassOut(){
        return Util.getClassOut();
    }
    /** 获取application classpath，以你定义的application.properties内容为准*/
    public String getClassPath(String name){
        return Util.getClassPath(name);
    }
    /** 获取keyfile文件夹所在的绝对路径 */
    public File getKeyFile(){
        return Util.getKeyFile();
    }
    /** 获取jar包所在路径 */
    public File jarInFile(){
        return Util.getJarInFIle();
    }
}
