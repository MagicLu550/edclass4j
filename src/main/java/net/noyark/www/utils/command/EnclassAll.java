package net.noyark.www.utils.command;


import net.noyark.www.utils.api.Pool;

public class EnclassAll implements CommandBase {

    @Override
    public Object execute(String[] args) {
        Pool.getClassCoder().recursiveEncode(args[0],args[1]);
        return "success";
    }

    @Override
    public String[] usage() {
        return new String[]{"加密classpath下全部文件(application指定)","comment 注释 keyfile 加密key文件"};
    }
}
