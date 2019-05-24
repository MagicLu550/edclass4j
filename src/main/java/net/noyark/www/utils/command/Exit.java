package net.noyark.www.utils.command;

import net.noyark.www.utils.ex.ShutDownException;


/**
 * 退出程序
 */

public class Exit implements CommandBase {


    @Override
    public Object execute(String[] args) {
        throw new ShutDownException();
    }
}
