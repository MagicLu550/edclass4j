package net.noyark.www.utils.command;

import net.noyark.www.utils.encode.Util;

/**
 * 设置配置文件路径，如同application.properties
 *
 * sppf filename
 */
public class Sppf implements CommandBase{

    @Override
    public Object execute(String[] args) {
        Util.setReadApplication(args[0]);
        return "set successfully";
    }
}
