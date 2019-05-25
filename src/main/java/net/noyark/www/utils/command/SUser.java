package net.noyark.www.utils.command;

import net.noyark.www.utils.api.Connector;

/**
 * 设置或者修改用户名
 */

public class SUser extends ConnectorCommand{

    public SUser(Connector connector) {
        super(connector);
    }

    @Override
    public Object execute(String[] args) {
        connector.setUserName(args[0]);
        return "设置数据库连接用户名成功"+args[0];
    }

    @Override
    public String[] usage() {
        return new String[]{"修改用户名","user_name 用户名"};
    }
}
