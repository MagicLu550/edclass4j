package net.noyark.www.utils;

import net.noyark.www.utils.api.Connector;
import net.noyark.www.utils.api.Pool;
import net.noyark.www.utils.command.*;
import net.noyark.www.utils.command.Random;
import net.noyark.www.utils.ex.ShutDownException;

import java.util.*;

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

    private static Map<String,String> vars = new HashMap<>();

    private static Connector connector;

    public static Map<String, CommandBase> commandBaseMap;


    static {
        commandBaseMap = new HashMap<>();
        connector = Pool.getConnector();
        registerCommand();
        vars.put("jarin",commandBaseMap.get("jarin").execute(new String[1]).toString());
        vars.put("keyfile",commandBaseMap.get("keyfile").execute(new String[1]).toString());
        vars.put("gclass",commandBaseMap.get("gclass").execute(new String[1]).toString());
    }

    public static void main(String[] args){
        if(args.length != 0){
            runCommand(args);
        }else{
            Message.info("启动PluginEmpowerSystem服务");
            new Thread(new CommandThread()).start();
        }
    }

    public static void registerCommand(){
        commandBaseMap.put("suser",new SUser(connector));
        commandBaseMap.put("spwd",new SPwd(connector));
        commandBaseMap.put("exit",new Exit());
        commandBaseMap.put("connect",new Connect(connector));
        commandBaseMap.put("save",new Save(connector));
        commandBaseMap.put("rkeys",new Random(connector));
        commandBaseMap.put("stable",new Stable(connector));
        commandBaseMap.put("ctable",new Ctable(connector));//创建表
        commandBaseMap.put("compare",new Compare(connector));
        commandBaseMap.put("dcclass",new Dcclass(connector));
        commandBaseMap.put("cckey",new Cckey());//cckey keyFileName
        commandBaseMap.put("eclass",new Eclass());//eclass keyFileName class1 class2 class3...
        commandBaseMap.put("dclass",new Dclass());
        commandBaseMap.put("declassall",new DeclassAll());
        commandBaseMap.put("enclassall",new EnclassAll());
        commandBaseMap.put("sppf",new Sppf());
        commandBaseMap.put("keyfile",new KeyFile());
        commandBaseMap.put("jarin",new Jarin());
        commandBaseMap.put("echo",new Echo());
        commandBaseMap.put("dev",new Dev());//jar
        commandBaseMap.put("gclass",new Gclass());//get classes
        commandBaseMap.put("jar",new Jar());
        commandBaseMap.put("ead",new Ead());
        commandBaseMap.put("help",new Help(new HashMap<>(commandBaseMap)));
    }


    public static class CommandThread implements Runnable{
        @Override
        public void run() {
            while(true){
                try{
                    String cmd = Message.cmd();
                    String[] args = cmd.split(" ");
                    runCommand(args);
                }catch (Exception e){
                    if (e instanceof ShutDownException){
                        Message.info("close");
                        System.exit(0);
                    }
                    e.printStackTrace();
                    Message.error(e.getMessage());
                }
            }
        }
    }

    public static void runCommand(String[] args){
        if(args[0].startsWith("$")){
            String[] right_left = args[0].split("=");
            String value = right_left[1];
            Set<Map.Entry<String,CommandBase>> set = commandBaseMap.entrySet();
            //变量指令只支持jarin keyfile的
            value = value
                    .replace("{jarin}",commandBaseMap.get("jarin").execute(new String[]{}).toString())
                    .replace("{keyfile}",commandBaseMap.get("keyfile").execute(new String[]{}).toString())
                    .replace("{gclass}",commandBaseMap.get("gclass").execute(new String[]{}).toString());

            vars.put(right_left[0].replace("$",""),value);
        }else{
            String[] alls = new String[args.length-1];
            System.arraycopy(args,1,alls,0,alls.length);
            Set<Map.Entry<String,String>> set = vars.entrySet();
            for(int i =0;i<alls.length;i++){
                for(Map.Entry<String,String> entry:set) {
                    alls[i] = alls[i].replace("${"+entry.getKey()+"}",entry.getValue());
                }
            }
            CommandBase commandInstance = commandBaseMap.get(args[0]);
            if(commandInstance != null){
                Object o = commandInstance.execute(alls);
                Message.info(o==null?"null":o.toString());
            }else {
                Message.error("no such command");
            }
        }
    }

}
