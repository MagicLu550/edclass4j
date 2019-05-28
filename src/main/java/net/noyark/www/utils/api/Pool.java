package net.noyark.www.utils.api;

import net.noyark.www.utils.DB_CONNECT;
import net.noyark.www.utils.DevJar;
import net.noyark.www.utils.encode.SimpleClassCoder;
import net.noyark.www.utils.safe.encode.SafeClassCoder;

public class Pool {

    static {
        connector = new DB_CONNECT();
        classCoder = new SimpleClassCoder();
        devJar = new DevJar();
        safeSimpleCoder = new SafeClassCoder();
    }

    private static DB_CONNECT connector;

    private static SimpleClassCoder classCoder;

    private static SafeClassCoder safeSimpleCoder;

    private static DevJar devJar;

    public static Connector getConnector() {
        return connector;
    }

    @Deprecated
    public static ClassCoder getClassCoder() {
        return classCoder;
    }

    public static ClassCoder getDESClassCoder(){
        return classCoder;
    }

    public static ClassCoder getAESClassCoder(){
        return safeSimpleCoder;
    }

    public static DevTool getDevJar() {
        return devJar;
    }

}
