package net.noyark.www.utils.command;

import net.noyark.www.utils.Connector;
import net.noyark.www.utils.DB_CONNECT;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 将设置的数据库信息保存到持久层
 */
public class Save extends ConnectorCommand {

    public Save(Connector connector) {
        super(connector);
    }

    public static final int USERNAME = 0;

    public static final int PASSWORD = 1;

    public static final int DB_NAME = 2;

    public static final int IP = 3;

    public static final int PORT = 4;

    public static final int START_TYPE = 5;

    public static final int TABLE = 6;

    //save fileName
    @Override
    public Object excute(String[] args) {

        DB_CONNECT db_connect = (DB_CONNECT) connector;

        File file = new File(args[0]);

        List<String> informationList = new ArrayList<>();

        informationList.add(db_connect.getUserName());//0
        informationList.add(db_connect.getPassword());//1
        informationList.add(db_connect.getDbName());//2
        informationList.add(db_connect.getIp());//3
        informationList.add(db_connect.getPort()+"");//4
        informationList.add(db_connect.getType().getStart());//5
        informationList.add(db_connect.getTable());//6
        try{
            FileUtils.writeLines(file,informationList);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "save successfully";
    }
}
