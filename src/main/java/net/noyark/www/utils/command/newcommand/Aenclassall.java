package net.noyark.www.utils.command.newcommand;

import net.noyark.www.utils.api.Pool;

/**
 * 加密全部classpath下的类文件
 */

public class Aenclassall implements SafeCommand{

    @Override
    public Object execute(String[] args) {
        Pool.getAESClassCoder().recursiveEncode(args[0],args[1]);
        return "加密";
    }

    @Override
    public String[] usage() {
        return new String[]{"aenclassall 加密全部classpath的类文件,以aes形式加密","comment 注释，随便写,keyfile key文件路径"};
    }
}
