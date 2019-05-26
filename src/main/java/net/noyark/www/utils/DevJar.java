package net.noyark.www.utils;


import net.noyark.www.utils.api.DevTool;
import net.noyark.www.utils.encode.Util;

import java.io.*;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 * 打包的代码= =
 *
 * 和打包普通压缩包区别不大，多了个识别文件，这样才能打包生效
 */

public class DevJar implements DevTool {

    public void devJar(String javaClassPath,String targetPath,String mainClass) throws IOException{
        Message.info("creating the jar file");
        targetPath = Util.getJarInFIle()+"/"+targetPath;
        int ind = targetPath.lastIndexOf("/");
        String targetDirPath;
        if(ind !=-1){
            targetDirPath = targetPath.substring(0,ind);
        }else{
            targetDirPath = targetPath;
        }
        File targetDir = new File(targetDirPath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        if(mainClass!=null){
            manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS,mainClass);
        }

        JarOutputStream target = new JarOutputStream(new FileOutputStream(targetPath), manifest);
        writeClassFile(new File(javaClassPath), target,javaClassPath);
        target.close();
        Message.info("success");
    }


    private void writeClassFile(File source, JarOutputStream target, String javaClassPath) throws IOException {
        BufferedInputStream in = null;
        try {
            if (source.isDirectory()) {
                String name = source.getPath().replace("\\", "/");
                if (!name.isEmpty()) {
                    if (!name.endsWith("/")) {
                        name += "/";
                    }
                    name = name.substring(javaClassPath.length());
                    JarEntry entry = new JarEntry(name);
                    entry.setTime(source.lastModified());
                    target.putNextEntry(entry);
                    target.closeEntry();
                }
                for (File nestedFile : source.listFiles())
                    writeClassFile(nestedFile, target,javaClassPath);
                return;
            }

            String middleName = source.getPath().replace("\\", "/").substring(javaClassPath.length());
            JarEntry entry = new JarEntry(middleName);
            entry.setTime(source.lastModified());
            target.putNextEntry(entry);
            in = new BufferedInputStream(new FileInputStream(source));

            byte[] buffer = new byte[1024];
            while (true) {
                int count = in.read(buffer);
                if (count == -1)
                    break;
                target.write(buffer, 0, count);
            }
            target.closeEntry();
        } finally {
            if (in != null)
                in.close();
        }
    }
}
