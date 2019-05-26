package net.noyark.www.utils.api;

import net.noyark.www.utils.DB_CONNECT;
import net.noyark.www.utils.DevJar;
import net.noyark.www.utils.encode.SimpleClassCoder;

public class Pool {

    static {
        connector = new DB_CONNECT();
        classCoder = new SimpleClassCoder();
        devJar = new DevJar();
    }

    private static DB_CONNECT connector;

    private static SimpleClassCoder classCoder;

    private static DevJar devJar;

    public static Connector getConnector() {
        return connector;
    }

    public static ClassCoder getClassCoder() {
        return classCoder;
    }

    public static DevTool getDevJar() {
        return devJar;
    }

}
