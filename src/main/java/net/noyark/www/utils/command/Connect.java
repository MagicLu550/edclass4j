package net.noyark.www.utils.command;

import net.noyark.www.utils.Connector;
import net.noyark.www.utils.DBTypes;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 该指令连接数据库
 */

public class Connect implements CommandBase {

    private Connector connector;

    public Connect(Connector connector){
        this.connector = connector;
    }

    @Override
    public Object excute(String[] args) {
        if (args.length == 2){
            //just ip dbName
            if(args[0].equals("-f")){
                try{
                    List<String> infor =  FileUtils.readLines(new File(args[1]),"UTF-8");
                    connector.setUserName(infor.get(0));
                    connector.setPassword(infor.get(1));
                    connect(infor.get(3),infor.get(2),infor.get(4),infor.get(5));
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else{
                connector.connect(args[0],args[1]);
            }
        }else if(args.length == 3){
            if(args[2].toUpperCase().equals("oracle")){
                connector.connect(args[0],args[1], DBTypes.ORACLE);
            }else if(args[2].toUpperCase().equals("sqlserver")){
                connector.connect(args[0],args[1],DBTypes.SQLSERVER);
            }else{
                connector.connect(args[0],args[1],DBTypes.MYSQL);
            }
        }else{
            connect(args);
        }
        return "connect successfully";
    }

    private void connect(String... args){
        if(args[2].toUpperCase().equals("oracle")){
            connector.connect(args[0],args[1],Integer.parseInt(args[2]),DBTypes.ORACLE);
        }else if(args[2].toUpperCase().equals("sqlserver")){
            connector.connect(args[0],args[1],Integer.parseInt(args[2]),DBTypes.SQLSERVER);
        }else{
            connector.connect(args[0],args[1],Integer.parseInt(args[2]),DBTypes.MYSQL);
        }
    }
}
