package net.noyark.www.utils.jar;

import net.noyark.www.utils.Message;
import net.noyark.www.utils.encode.DecryptStart;
import net.noyark.www.utils.encode.Util;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarException;
import java.util.jar.JarFile;
/**
 * 将加密的jar包运行
 */

public class DecodeJar {

    private File jarFile;

    private String keyFile;

    public DecodeJar(String jarFileName,String keyFile) {
        this.jarFile = new File(jarFileName);
        this.keyFile = keyFile;
    }

    public void runJar(String[] args) throws IllegalAccessException,NoSuchMethodException, InvocationTargetException {
        Class<?> claz = getMainClass();
        if(claz == null){
            Message.error("没有主清单目录");
            return;
        }
        claz.getMethod("main",String[].class).invoke(null,(Object) args);
    }

    public Class<?> getMainClass(){
        try{
            JarFile jarFile = new JarFile(this.jarFile);
            Iterator<JarEntry> entries = jarFile.stream().iterator();
            InputStream MF_STREAM = null;
            while (entries.hasNext()){
                JarEntry jar = entries.next();
                if("META-INF/MANIFEST.MF".equals(jar.getName())){
                    MF_STREAM = jarFile.getInputStream(jar);
                    break;
                }
            }
            if(MF_STREAM == null){
                throw new JarException("这个jar文件无法被运行，由于它没有MANIFEST.MF");
            }else{
                String main_class = jarFile.getManifest().getMainAttributes().get(Attributes.Name.MAIN_CLASS).toString();
                if(main_class!=null){
                    return getDecodeClass(main_class);
                }else{
                    return null;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Class<?> getDecodeClass(String main_class) throws Exception{
        return getDecodeClass(main_class,null);
    }

    public Class<?> getDecodeClass(String main_class,ClassLoader parent) throws Exception{
        JarFile jarFile = new JarFile(this.jarFile);
        URLClassLoader loader = new URLClassLoader(new URL[]{this.jarFile.toURI().toURL()});
        String classname = main_class.replace(".","/");
        if(!classname.endsWith(".class")){
            classname = classname+".class";
        }
        InputStream main = loader.getResourceAsStream(classname);
        Iterator<JarEntry> mainGets = jarFile.stream().iterator();
        while (mainGets.hasNext()){
            JarEntry entry = mainGets.next();
            if(entry.getName().replaceAll("/|\\\\",".").equals(main_class+".class")){
                if(parent== null){
                    DecryptStart start = new DecryptStart(Util.readKey(keyFile),main,entry.getSize());
                    return start.loadClass(main_class);
                }else{
                    DecryptStart start = new DecryptStart(Util.readKey(keyFile),main,entry.getSize(),parent);
                    return start.loadClass(main_class);
                }
            }
        }
        return null;
    }
}
