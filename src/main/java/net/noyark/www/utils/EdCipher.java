package net.noyark.www.utils;

import java.io.*;

/**
 * 加解密类
 * 代码来源 https://www.cnblogs.com/chuckjam/p/9456402.html
 */
public class EdCipher {

    private String encryptFolder = "encrypt";

    /**
     * 加密方法
     * @param name 需要加密的文件名
     */
    public void encryptClass(String name) {
        String path = getFilePath(name);
        // classFile为待加密的class文件
        File classFile = new File(path);
        if (!classFile.exists()) {
            // TODO 如果文件不存在，做相应的处理。一般情况下都是抛出异常；
        } else {
            // folder 是准备在待加密的文件也就是classFIle的同级目录下创建一个文件夹，里面放着加密后的文件
            File folder = new File(classFile.getParent() + File.separator + encryptFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        // cipheredClass 为加密后文件的全路径文件名
        String cipheredClass = classFile.getParent() + File.separator + encryptFolder + File.separator + classFile.getName();
        try (
                FileInputStream fileInputStream = new FileInputStream(classFile);
                BufferedInputStream bis = new BufferedInputStream(fileInputStream);
                FileOutputStream fileOutputStream = new FileOutputStream(cipheredClass);
                BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream)
        ) {
            int data;
            while ((data = bis.read()) != -1) {
                bos.write(data ^ 0xFF);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 现在将原来未加密的文件删除
        classFile.delete();

        //下面这一句在文件后面加了一个“en”,最后生成的文件就是xxx.classen，这样做的目的是为了在启动服务器的时候
        //tomcat会自动检查classespath下的class文件，如果我不加上一个“en”，那么改加密文件就会被tomcat扫描到。
        //如果被扫描到了，但是它又是一个被加密后的文件，头部信息被修改了，那么tomcat就会报错，启动不起来。这算是一个小技巧。
        File oldFile = new File(path + "en");
        if (oldFile.exists()) {
            oldFile.delete();
        }
        File cipheredFile = new File(cipheredClass);
        cipheredFile.renameTo(oldFile);
        cipheredFile.getParentFile().delete();
    }

    /**
     * 解密方法
     * @param name 需要解密的文件名
     */
    protected byte[] decryptClass(String name) {
        String path;
        if (!name.contains(".class")) {
            path = getDefFilePath(name);
        } else {
            path = name;
        }
        File encryptedClassFile = new File(path);
        if (!encryptedClassFile.exists()) {
            System.out.println("decryptClass() File:" + path + " not found!");
            return null;
        }
        byte[] result = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(encryptedClassFile));
            bos = new ByteArrayOutputStream();
            int data;
            while ((data = bis.read()) != -1) {
                bos.write(data ^ 0xFF);
            }
            bos.flush();
            result = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //获取加密前文件的绝对路径
    private String getFilePath(String name) {
        String path;
        String str = name.substring(name.lastIndexOf(".") + 1, name.length()) + ".class";
        path = EdCipher.class.getResource(str).toString();
        path = path.substring(path.indexOf("file:/") + "file:/".length(), path.length());
        if (System.getProperty("os.name").toUpperCase().contains("LINUX")) {
            path = File.separator + path;
        }
        return path;
    }

    //获取加密后文件的绝对路径
    private String getDefFilePath(String name) {
        String path;
        String str = name.substring(name.lastIndexOf(".") + 1, name.length()) + ".classen";
        path = EdCipher.class.getResource(str).toString();
        path = path.substring(path.indexOf("file:/") + "file:/".length(), path.length());
        return path;
    }

}