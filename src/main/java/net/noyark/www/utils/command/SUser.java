package net.noyark.www.utils.command;

import net.noyark.www.utils.Connector;

/**
 * 设置或者修改用户名
 */

public class SUser implements CommandBase{

    private Connector connector;

    public SUser(Connector connector){
        this.connector = connector;
    }

    @Override
    public Object excute(String[] args) {
        connector.setUserName(args[0]);
        return "设置数据库连接用户名成功"+args[0];
    }
}
