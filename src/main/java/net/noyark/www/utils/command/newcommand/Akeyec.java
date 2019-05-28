package net.noyark.www.utils.command.newcommand;

import net.noyark.www.utils.api.Pool;

/**
 * 同时创建key
 */
public class Akeyec implements SafeCommand{

    @Override
    public Object execute(String[] args) {
        String[] classes = new String[args.length-1];
        System.arraycopy(args,1,classes,0,classes.length);
        Pool.getAESClassCoder().encode(args[0],classes);
        return "创建key并加密成功";
    }

    @Override
    public String[] usage() {
        return new String[]{"akeyec 可以创建key文件并同时加密","keyfile key文件路径 classname... 类名"};
    }
}
