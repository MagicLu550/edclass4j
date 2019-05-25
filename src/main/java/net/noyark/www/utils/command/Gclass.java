package net.noyark.www.utils.command;

import net.noyark.www.utils.encode.Util;

public class Gclass implements CommandBase{

    @Override
    public Object execute(String[] args) {
        return Util.getClassFile();
    }

    @Override
    public String[] usage() {

        return new String[]{"获取classes文件路径，即程序默认加密的地方"};
    }
}
