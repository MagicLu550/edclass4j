package net.noyark.www.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {

    BasicDataSource dataSource;

   public DBUtils(DBTypes types,String name,String password,String dbName,String ip,int port){
        dataSource = new BasicDataSource();

        dataSource.setDriverClassName(types.getDriver());
        dataSource.setUsername(name);
        dataSource.setPassword(password);
        if(types.equals(DBTypes.SQLSERVER)){
            //1433
            dataSource.setUrl("jdbc:"+types.getStart()+"://"+ip+":"+port+";DatabaseName="+dbName);
        }else if(types.equals(DBTypes.ORACLE)){
            //1521
            dataSource.setUrl("jdbc:"+types.getStart()+":thin:@"+ip+":"+port+":"+dbName);
        }else if(types.equals(DBTypes.MYSQL)){
            //3306
            dataSource.setUrl("jdbc:"+types.getStart()+"://"+ip+":"+port+"/"+dbName);
        }
    }

    public Connection getConnection() throws SQLException {
       return dataSource.getConnection();
    }

}
