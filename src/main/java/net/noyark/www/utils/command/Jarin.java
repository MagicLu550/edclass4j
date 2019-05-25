package net.noyark.www.utils.command;

import net.noyark.www.utils.encode.Util;

public class Jarin implements CommandBase{

    @Override
    public Object execute(String[] args) {
        return Util.getJarInFIle();
    }

    @Override
    public String[] usage() {
        return new String[]{"获取jar包所在路径","${jarin}可以嵌入指令"};
    }
}
