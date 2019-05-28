package net.noyark.www.utils.api;


import net.noyark.www.utils.CodeReflectSet;
import net.noyark.www.utils.Coder;
import net.noyark.www.utils.encode.Util;

import java.io.File;
import java.util.List;
public abstract class ClassCoder implements Coder {

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

    //加密整个class系统
    public List<Class<?>> recursiveEncode(String mainClass,String keyFile){
        return CodeReflectSet.getDESReflectSet().load(this,keyFile,false,mainClass);
    }
    //加密整个class系统
    public List<Class<?>> recursiveDecode(String mainClass,String keyFile){
        return CodeReflectSet.getDESReflectSet().load(this,keyFile,true,mainClass);
    }


}
