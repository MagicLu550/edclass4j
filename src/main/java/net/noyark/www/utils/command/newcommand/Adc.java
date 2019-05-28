package net.noyark.www.utils.command.newcommand;

import net.noyark.www.utils.api.Pool;

/**
 * 解密单个类
 */

public class Adc implements SafeCommand {

    @Override
    public Object execute(String[] args) {


        Pool.getAESClassCoder().decode(args[0],args[1],true);

        return null;
    }

    @Override
    public String[] usage() {
        return new String[]{"adc 解密单个类","file_name key文件 classname 类名"};
    }
}
