package net.noyark.www.utils.encode;

import net.noyark.www.utils.Message;

import java.io.*;
import java.security.*;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.crypto.*;
import javax.crypto.spec.*;

/**
 * 加密的代码
 * 流程很简单，通过api生成key，通过key把class文件内容读出来编码再写进去
 * 之后一个编码的文件就诞生的
 * 怎么解码，也很简单，把key放进去，然后用定制的classloader把class对象取出，api根据key解密，就ok了
 *解密考虑问题：就是万一是系统的class，那么分三步走，总有一款适合你
 */

public class DecryptStart extends ClassLoader
{
    private Cipher cipher;

    private InputStream in;

    private long len;

    public DecryptStart(SecretKey key) throws GeneralSecurityException {
       setKey(key);
    }
    public DecryptStart(SecretKey key,ClassLoader parent) throws GeneralSecurityException{
        super(parent);
        setKey(key);
    }

    public void setKey(SecretKey key) throws GeneralSecurityException{
        String algorithm = "DES";
        SecureRandom sr = new SecureRandom();
        if(Util.getDecodeMessageOut()){
            Message.info("[DecryptStart: creating cipher]");
        }
        cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
    }

    public DecryptStart(SecretKey key,InputStream in,long len) throws GeneralSecurityException{
        setKey(key);
        setIn(in);
        this.len = len;
    }

    public DecryptStart(SecretKey key,InputStream in,long len,ClassLoader parent) throws GeneralSecurityException{
        super(parent);
        setKey(key);
        setIn(in);
        this.len = len;
    }

    public void setIn(InputStream in){
        this.in = in;
    }

    /**
     * 把之前密钥放入，接着解密，通过定制classloader加载类，就ok了
     * @param args
     * @param executeMain
     * @return
     * @throws Exception
     */
    public static Class<?> decode(String args[],boolean executeMain) throws Exception {
        String keyFilename = args[0];
        String appName = args[1];

        // 传递给应用本身的参数
        String realArgs[] = new String[args.length-2];
        System.arraycopy( args, 2, realArgs, 0, args.length-2 );

        // 创建解密的ClassLoader
        DecryptStart dr = new DecryptStart(Util.readKey(keyFilename));

        // 创建应用主类的一个实例，通过ClassLoader装入它
        if(Util.getDecodeMessageOut()){
            Message.info("[DecryptStart: loading "+appName+"]");
        }

        Class clasz = dr.loadClass(appName.replaceAll("/|\\\\","."));

        executeMain(clasz,realArgs,appName,executeMain);
        return clasz;
    }

    private static void executeMain(Class<?> clasz,String[] realArgs,String appName,boolean executeMain){
        if(executeMain){
            try{
                /*反射调用main方法
                 *
                 */
                Class mainArgs[] = { (new String[1]).getClass() };
                Method main = clasz.getMethod("main", mainArgs);

                // 创建一个包含main()方法参数的数组
                Object argsArray[] = { realArgs };
                Message.info("[DecryptStart: running "+appName+".main()]");

                // 调用main()
                main.invoke(null, argsArray);
            }catch (Exception e){
                Message.error("没有找到main方法应用程序入口，无法执行");
            }
        }
    }

    public Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
        try {
            // 要创建的Class对象
            Class clasz;

            // 必需的步骤1：如果类已经在系统缓冲之中，不必再次装入它
            clasz = findLoadedClass(name);

            if (clasz != null)
                return clasz;

            // 下面是定制部分
            try{
                String filename = Util.getClassPath(name);
                //读取经过加密的类文件
                byte classData[];
                if(in ==null){
                    classData = Util.readFile(filename+".class");
                }else{
                    classData = Util.readFile(in,len);
                }
                if(classData != null){
                    byte decryptedClassData[] = cipher.doFinal(classData);  //解密
                    clasz = defineClass( name, decryptedClassData, 0, decryptedClassData.length); // 再把它转换成一个类
                    if(Util.getDecodeMessageOut()) {
                        Message.info("[DecryptStart: decrypting class " + name + "]");
                    }
                }
            }catch(FileNotFoundException e){
            }

            // 必需的步骤2：如果上面没有成功
            // 尝试用默认的ClassLoader装入它
            if (clasz == null)
                clasz = findSystemClass(name);

            // 必需的步骤3：如有必要，则装入相关的类
            if (resolve && clasz != null)
                resolveClass(clasz);
            return clasz;//把类返回给调用者

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}