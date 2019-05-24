package net.noyark.www.utils.command;

import net.noyark.www.utils.encode.SimpleClassCoder;

public class EnclassAll implements CommandBase {

    @Override
    public Object execute(String[] args) {
        SimpleClassCoder.getClassCoder().recursiveEncode(args[0],args[1]);
        return "success";
    }
}
