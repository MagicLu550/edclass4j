package net.noyark.www.utils;

import net.noyark.www.utils.command.*;
import net.noyark.www.utils.ex.ShutDownException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

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
 * connect -f fileName 通过保存地址连接
 *
 * reboot会关闭服务器 而且会至多再会启动一次
 *
 * stable 设置表
 *
 * ctable会创建标准表
 */

public class JarEncode {

    private static Connector connector;

    public static Map<String, CommandBase> commandBaseMap;

    public static CountDownLatch latch = new CountDownLatch(1);

    static {
        commandBaseMap = new HashMap<>();
        connector = DB_CONNECT.getConnector();
        registerCommand();
    }

    public static void main(String[] args){
        Message.info("启动PluginEmpowerSystem服务");
        new Thread(new CommandThread()).start();
        try{
            latch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        new Thread(new CommandThread()).start();
    }

    public static void registerCommand(){
        commandBaseMap.put("suser",new SUser(connector));
        commandBaseMap.put("spwd",new SPwd(connector));
        commandBaseMap.put("exit",new Exit());
        commandBaseMap.put("reboot",new Reboot(latch));
        commandBaseMap.put("connect",new Connect(connector));
        commandBaseMap.put("save",new Save(connector));
        commandBaseMap.put("rkeys",new Random(connector));
        commandBaseMap.put("stable",new Stable(connector));
        commandBaseMap.put("ctable",new Ctable(connector));//创建表
        commandBaseMap.put("compare",new Compare(connector));
        commandBaseMap.put("cckey",new Cckey());//cckey keyFileName
        //TODO 明天用我的reflectset库实现全部加密
        //eclass all
        commandBaseMap.put("eclass",new Eclass());//eclass keyFileName class1 class2 class3...
        commandBaseMap.put("dclass",new Dclass());
        commandBaseMap.put("dcclass",new Dcclass(connector));
    }

    public static class CommandThread implements Runnable{
        @Override
        public void run() {
            try{
                while(true){
                    String cmd = Message.cmd();
                    String arg = Message.input();
                    String[] alls = arg.trim().split(" ");
                    CommandBase commandInstance = commandBaseMap.get(cmd);
                    if(commandInstance != null){
                        Object o = commandInstance.execute(alls);
                        Message.info(o==null?"null":o.toString());
                    }else {
                        Message.error("no such command");
                    }
                }
            }catch (ShutDownException e){
                Message.info("closed");
            }
        }
    }

}
