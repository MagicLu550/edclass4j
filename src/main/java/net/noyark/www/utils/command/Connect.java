package net.noyark.www.utils.command;

import net.noyark.www.utils.api.Connector;
import net.noyark.www.utils.DBTypes;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 该指令连接数据库
 */

public class Connect extends ConnectorCommand{

    public Connect(Connector connector) {
        super(connector);
    }

    private static final int ARR_IP = 0;

    private static final int ARR_DB_NAME = 1;

    private static final int ARR_PORT = 2;

    private static final int ARR_START_TYPE = 3;

    @Override
    public Object execute(String[] args) {
        if (args.length == 2){
            //just ip dbName
            if(args[0].equals("-f")){
                try{
                    List<String> infor =  FileUtils.readLines(new File(args[1]),"UTF-8");
                    connector.setUserName(infor.get(Save.USERNAME));
                    connector.setPassword(infor.get(Save.PASSWORD));
                    //ip db start port
                    connect(infor.get(Save.IP),infor.get(Save.DB_NAME),infor.get(Save.PORT),infor.get(Save.START_TYPE));
                    connector.setTable(infor.get(Save.TABLE));
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


    @Override
    public String[] usage() {
        return new String[]{"用于连接远程授权端","-f filename 直接导入标准的配置文件","ip dbname 默认为mysql数据库","ip dbname type 选择数据库类型，有mysql oracle sqlserver"};
    }

    private void connect(String... args){
        if(args[ARR_START_TYPE].toUpperCase().equals("oracle")){
            connector.connect(args[ARR_IP],args[ARR_DB_NAME],Integer.parseInt(args[ARR_PORT]),DBTypes.ORACLE);
        }else if(args[ARR_START_TYPE].toUpperCase().equals("sqlserver")){
            connector.connect(args[ARR_IP],args[ARR_DB_NAME],Integer.parseInt(args[ARR_PORT]),DBTypes.SQLSERVER);
        }else{
            connector.connect(args[ARR_IP],args[ARR_DB_NAME],Integer.parseInt(args[ARR_PORT]),DBTypes.MYSQL);
        }
    }
}
