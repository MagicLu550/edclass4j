package net.noyark.www.utils.command;

import net.noyark.www.utils.Connector;
import net.noyark.www.utils.DBTypes;

public class Connect implements CommandBase {

    private Connector connector;

    public Connect(Connector connector){
        this.connector = connector;
    }

    @Override
    public Object excute(String[] args) {
        if (args.length == 2){
            //just ip dbName
            connector.connect(args[0],args[1]);
        }else if(args.length == 3){
            if(args[2].toUpperCase().equals("oracle")){
                connector.connect(args[0],args[1], DBTypes.ORACLE);
            }else if(args[2].toUpperCase().equals("sqlserver")){
                connector.connect(args[0],args[1],DBTypes.SQLSERVER);
            }else{
                connector.connect(args[0],args[1],DBTypes.MYSQL);
            }
        }else{
            if(args[2].toUpperCase().equals("oracle")){
                connector.connect(args[0],args[1],Integer.parseInt(args[2]),DBTypes.ORACLE);
            }else if(args[2].toUpperCase().equals("sqlserver")){
                connector.connect(args[0],args[1],Integer.parseInt(args[2]),DBTypes.SQLSERVER);
            }else{
                connector.connect(args[0],args[1],Integer.parseInt(args[2]),DBTypes.MYSQL);
            }
        }
        return "connect successfully";
    }
}
