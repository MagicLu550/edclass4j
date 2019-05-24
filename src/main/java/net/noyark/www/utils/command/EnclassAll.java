package net.noyark.www.utils.command;


import net.noyark.www.utils.api.Pool;

public class EnclassAll implements CommandBase {

    @Override
    public Object execute(String[] args) {
        Pool.getClassCoder().recursiveEncode(args[0],args[1]);
        return "success";
    }
}
