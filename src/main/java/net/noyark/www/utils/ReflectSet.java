package net.noyark.www.utils;

import net.noyark.www.utils.api.ClassCoder;
import net.noyark.www.utils.encode.SimpleClassCoder;
import net.noyark.www.utils.encode.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface ReflectSet {

    List<Class<?>> load(ClassCoder classCoder, String keyFile, boolean isDecode, String mainClass);

    default String[] getAllClassName(){
        List<String> list = new ArrayList<>();
        File file = new File(Util.getClassPath(""));
        List<File> fileName = loadClass(file);
        for(File fn:fileName) {
            File[] fs = fn.listFiles();
            if(fs!=null) {
                for(File f1:fs) {
                    if(f1.getName().endsWith(".class")) {
                        String classpath = f1.getPath();
                        classpath = classpath.substring(classpath.indexOf("classes")+"classes".length()+1,classpath.indexOf(".class")).replaceAll("/",".");
                        list.add(classpath);
                    }
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * It can get Package and Classes next the root package
     * @param file the classpath file
     * @return the file about classes and package
     */

    private List<File> loadClass(File file) {
        List<File> allFiles = new ArrayList<File>();
        File[] files = file.listFiles();
        if(files!=null) {
            for(File f:files) {
                if(f.getName().endsWith(".class")) {
                    allFiles.add(f);
                }
            }
        }
        searchFile(files, allFiles);
        return allFiles;
    }

    /**
     * It can get all of the file about package
     * @param files the file objects
     * @param allFiles the package file
     */

    private void searchFile(File[] files,List<File> allFiles) {
        if(files!=null) {
            for(File f:files) {
                if(f.isDirectory()){
                    allFiles.add(f);
                    File[] files2 = f.listFiles();
                    searchFile(files2, allFiles);
                }
            }
        }
    }
}
