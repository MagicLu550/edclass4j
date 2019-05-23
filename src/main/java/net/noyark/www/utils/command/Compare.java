package net.noyark.www.utils.command;

import net.noyark.www.utils.Connector;

import java.sql.SQLException;

public class Compare extends ConnectorCommand{

    public Compare(Connector connector) {
        super(connector);
    }

    @Override
    public Object excute(String[] args) {
        try{
            return connector.compareKey(args[0],args[1],Integer.parseInt(args[2]));
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
