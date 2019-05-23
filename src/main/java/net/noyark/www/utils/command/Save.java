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

    //save fileName
    @Override
    public Object excute(String[] args) {

        DB_CONNECT db_connect = (DB_CONNECT) connector;

        File file = new File(args[0]);

        List<String> informationList = new ArrayList<>();

        informationList.add(db_connect.getUserName());
        informationList.add(db_connect.getPassword());
        informationList.add(db_connect.getDbName());
        informationList.add(db_connect.getIp());
        informationList.add(db_connect.getPort()+"");
        informationList.add(db_connect.getType().getStart());
        informationList.add(db_connect.getTable());
        try{
            FileUtils.writeLines(file,informationList);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "save successfully";
    }
}
