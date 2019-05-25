package net.noyark.www.utils.command;

import net.noyark.www.utils.encode.Util;

public class KeyFile implements CommandBase {

    @Override
    public Object execute(String[] args) {
        return Util.getKeyFile();
    }

    @Override
    public String[] usage() {
        return new String[]{"获取key解析的路径，可以用于decode相关"};
    }
}
