package net.noyark.www.utils.encode;

import net.noyark.www.utils.Message;

import java.io.*;
import java.util.Properties;

public class Util
{
    private static InputStream readApplication;
    // 把文件读入byte数组
    static public byte[] readFile(String filename) throws IOException {
        File file = new File(filename);
        long len = file.length();
        byte data[] = new byte[(int)len];
        FileInputStream fin = new FileInputStream(file);
        int r = fin.read(data);
        if (r != len)
            throw new IOException("Only read "+r+" of "+len+" for "+file);
        fin.close();
        return data;
    }

    // 把byte数组写出到文件
    static public void writeFile(String filename, byte data[]) throws IOException {
        FileOutputStream fout = new FileOutputStream(filename);
        fout.write(data);
        fout.close();
    }

    /**
     * now:file
     * @param file
     */
    public static void setReadApplication(String file){
        try{
            String in;
            if(file.startsWith("now:")){
                in = new File(Util.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile()+"/"+file.substring(file.indexOf(":")+1);
            }else{
                in = new File(file).getPath();
            }
            Message.info(in);
            readApplication = new FileInputStream(in);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static File getKeyFile(){
        return new File(getJarInFIle()+"/keyfile");
    }

    public static File getJarInFIle(){
        return new File(Util.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile();
    }

    public static String getClassPath(String name){
        String filename = null;
        try{
            Properties properties = new Properties();
            InputStream in;
            //自定义配置文件位置
            if(readApplication == null){
                in = Util.class.getResourceAsStream("application.properties");
            }else{
                in = readApplication;
            }
            if(in!=null){
                properties.load(in);
                String classpath = properties.getProperty("classpath");
                if(classpath == null){
                    classpath = "target/classes/";
                }
                String replace = name.replace(".","/");
                filename = classpath.endsWith("/")?classpath+replace:classpath+"/"+replace;
            }else{
                filename = "target/classes/"+name.replace(".","/");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return filename;
    }
}
