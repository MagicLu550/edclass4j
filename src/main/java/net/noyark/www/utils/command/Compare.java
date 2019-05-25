package net.noyark.www.utils.command;

import net.noyark.www.utils.api.Connector;

import java.sql.SQLException;

public class Compare extends ConnectorCommand{

    public Compare(Connector connector) {
        super(connector);
    }

    @Override
    public Object execute(String[] args) {
        try{
            return connector.compareKey(args[0],args[1],Integer.parseInt(args[2]));
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String[] usage() {
        return new String[]{"用于从远程数据库找到这个key是否存在","ip 绑定服务器ip地址 port 绑定服务器端口"};
    }
}
