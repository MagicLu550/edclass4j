package net.noyark.www.utils;


import java.io.InputStream;

public interface Connector {

    /**
     * 不定端口
     * @param ip
     * @param dbName
     * @param port
     * @param types
     */

    void connect(String ip,String dbName,int port,DBTypes types);

    /**
     * 默认连接mysql
     * @param ip
     * @param dbName
     */

    void connect(String ip,String dbName);

    /**
     * 使用默认端口
     * @param ip
     * @param dbName
     * @param dbTypes
     */

    void connect(String ip,String dbName,DBTypes dbTypes);

    /**
     * 设置用户名
     * @param userName
     */

    void setUserName(String userName);

    /**
     * 设置密码
     * @param password
     */

    void setPassword(String password);


    /**
     * 设置当前的表
     * @param table
     */

    void setTable(String table);

    /**
     * 创建表
     */
    boolean createKeyTable(String table);


    /**
     * 制定配置文件和配置字段，查找序列号是否符合要求,前提是设置了制定ip地址
     * 表的结构要求是
     * id        key            ip              port
     * primary  unique key
     * int      text           varchar(50)       varchar(10)
     * serverIp是指当前授权的ip地址
     * serverPort是指当前的授权port
     *
     * 返回是否已经授权
     */
    boolean compareKey(String keyFile,String keyName,String serverIp,int serverPort);

    boolean compareKey(InputStream in, String keyName, String serverIp, int serverPort);
    /**
     * 关闭db服务
     */
    void close();
}
