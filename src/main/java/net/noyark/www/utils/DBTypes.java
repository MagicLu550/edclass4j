package net.noyark.www.utils;

public enum DBTypes {

    MYSQL("com.mysql.jdbc.Driver","mysql",3306),
    ORACLE("oracle.jdbc.driver.OracleDriver","oracle",1521),
    SQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver","sqlserver",1433);

    private String driver;

    private String start;

    private int port;
    DBTypes(String driver,String start,int port){
        this.driver = driver;
        this.start = start;
        this.port = port;
    }

    public String getDriver(){
        return driver;
    }

    public String getStart() {
        return start;
    }

    public int getPort() {
        return port;
    }
}
