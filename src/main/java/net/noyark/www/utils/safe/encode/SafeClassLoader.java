package net.noyark.www.utils.safe.encode;


import net.noyark.www.utils.encode.Util;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class SafeClassLoader extends ClassLoader {

    private String keyFile;

    private InputStream in;

    private long len;

    public SafeClassLoader(String keyFile){
        this.keyFile = keyFile;
    }

    public SafeClassLoader(String keyFile,ClassLoader parent){
        super(parent);
        this.keyFile = keyFile;
    }

    public SafeClassLoader(String keyFile,InputStream in,long len,ClassLoader parent){
        super(parent);
        this.keyFile = keyFile;
        this.len = len;
        setInputStream(in);
    }

    public SafeClassLoader(String keyFile,InputStream in,long len){
        this(keyFile);
        this.len = len;
        setInputStream(in);
    }

    public void setInputStream(InputStream in){
        this.in = in;
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        Class clasz;


        clasz = findLoadedClass(name);

        if (clasz != null)
            return clasz;

        //加密部分

        // 下面是定制部分
        try{
            String filename = Util.getClassPath(name);
            //读取经过加密的类文件
            byte[] classData;
            if(in ==null){
                classData = Util.readFile(filename+".class");
            }else{
                classData = Util.readFile(in,len);
            }
            if(classData!=null){
                //keyfile读取
                byte[] decryptedClassData = CodeUtils.aesDecrypt(classData,CodeUtils.readKey(keyFile)); //解密
                clasz = defineClass( name, decryptedClassData, 0, decryptedClassData.length); // 再把它转换成一个类
            }
        }catch(Exception e){
            if(!(e instanceof FileNotFoundException)){
                e.printStackTrace();
            }
        }

        if (clasz == null)
            clasz = findSystemClass(name);


        if (resolve && clasz != null)
            resolveClass(clasz);
        return clasz;
    }
}
