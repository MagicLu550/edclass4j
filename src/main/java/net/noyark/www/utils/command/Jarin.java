package net.noyark.www.utils.command;

import net.noyark.www.utils.encode.Util;

public class Jarin implements CommandBase{

    @Override
    public Object execute(String[] args) {
        return Util.getJarInFIle();
    }
}
