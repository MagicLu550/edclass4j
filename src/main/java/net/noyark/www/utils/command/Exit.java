package net.noyark.www.utils.command;

/**
 * 退出程序
 */

public class Exit implements CommandBase {

    @Override
    public Object excute(String[] args) {
        System.exit(0);
        return "暂停";
    }
}
