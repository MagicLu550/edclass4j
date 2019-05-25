package net.noyark.www.utils.jar;

import java.net.URL;
import java.net.URLClassLoader;

public class EncodeClassLoader extends URLClassLoader {

    private String keyFile;

    public EncodeClassLoader(URL[] urls, ClassLoader parent,String keyFile) {
        super(urls, parent);
        this.keyFile = keyFile;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        return super.loadClass(name, resolve);
    }
}
