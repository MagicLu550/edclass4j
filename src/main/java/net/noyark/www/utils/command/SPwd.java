package net.noyark.www.utils.command;

import net.noyark.www.utils.api.Connector;

/**
 * 设置或者修改密码
 */

public class SPwd extends ConnectorCommand{

    public SPwd(Connector connector) {
        super(connector);
    }

    @Override
    public Object execute(String[] args) {
        connector.setPassword(args[0]);
        return "设置数据库连接密码成功"+args[0].replaceAll(".","\\*");
    }
}
