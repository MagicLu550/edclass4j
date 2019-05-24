package net.noyark.www.utils.api;


import java.io.File;
import java.util.List;

public interface ClassCoder {

    void encode(String fileName,String... classname);

    Class<?> decode(String keyFile,String classname,boolean executeMain);
    //不执行main
    Class<?> decode(String keyFile,String classname);
    //加密整个class系统
    List<Class<?>> recursiveEncode(String mainClass, String keyFile);
    //加密整个class系统
    List<Class<?>> recursiveDecode(String mainClass,String keyFile);



    /** 设置读取classpath的路径，即包前面的根路径，如果不设置，默认为在项目资源文件 */

    void setApplicationFile(String file);
    /** 获取class文件输出路径，如果为this，则是覆盖现行的路径 */
    String  getClassOut();
    /** 获取application classpath，以你定义的application.properties内容为准*/
    String getClassPath(String name);
    /** 获取keyfile文件夹所在的绝对路径 */
    File getKeyFile();
    /** 获取jar包所在路径 */
    File jarInFile();
}
