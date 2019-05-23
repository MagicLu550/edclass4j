package net.noyark.www.utils;

import net.noyark.www.utils.command.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 该类负责将jar包进行加密处理和在数据库生成密钥
 *
 * suser userName 设置数据库用户名
 * spwd password 设置密码
 * sdb dbName 设置库名
 * stb tableName 设置表名
 * connect 连接
 * connect ip dbName port
 * connect ip port type
 * connect ip dbName
 * connect ip dbName port type
 */

public class JarEncode {

    private static Connector connector;

    public static Map<String, CommandBase> commandBaseMap;

    static {
        commandBaseMap = new HashMap<>();
        connector = DB_CONNECT.getConnector();
        registerCommand();
    }

    public static void main(String[] args){
        Message.info("启动PluginEmpowerSystem服务");

        new Thread(()->{
            while(true){
                String cmd = Message.cmd();
                String arg = Message.input();
                String[] alls = arg.trim().split(" ");
                CommandBase commandInstance = commandBaseMap.get(cmd);
                if(commandInstance != null){
                    Object o = commandInstance.excute(alls);
                    Message.info(o.toString());
                }else {
                    Message.error("no such command");
                }
            }
        }).start();
    }

    public static void registerCommand(){
        commandBaseMap.put("suser",new SUser(connector));
        commandBaseMap.put("spwd",new SPwd(connector));
        commandBaseMap.put("exit",new Exit());
        commandBaseMap.put("connect",new Connect(connector));
    }

}
