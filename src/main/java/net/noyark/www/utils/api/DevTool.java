package net.noyark.www.utils.api;

import java.io.IOException;

public interface DevTool {
    /**
     * 压缩jar
     * @param javaClassPath
     * @param targetPath
     * @param mainClass
     * @throws IOException
     */
    void devJar(String javaClassPath,String targetPath,String mainClass) throws IOException;
}
