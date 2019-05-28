package net.noyark.www.utils;

public interface Coder {

    enum DecryptMethod{
        AES("AES"),
        DES("DES");

        private String method;
        DecryptMethod(String method){
            this.method = method;
        }

        public String getMethod() {
            return method;
        }
    }

    void encode(String fileName,String... classname);

    Class<?> decode(String keyFile,String classname,boolean executeMain);
    //不执行main
    Class<?> decode(String keyFile,String classname);

    Class<?> getClassInJar(String jarFile,String classname,String keyFile);

    Class<?> getClassInJar(String jarFile,String classname,String keyFile,ClassLoader loader);

}
