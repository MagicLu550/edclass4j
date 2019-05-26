package net.noyark.www.utils.command;

import net.noyark.www.utils.api.Pool;
import net.noyark.www.utils.encode.Util;


/**
 * 加密并打包
 * ead main_class key_file target
 */

public class Ead implements CommandBase{

    @Override
    public Object execute(String[] args) {
        try{
            Pool.getClassCoder().recursiveEncode(args[0],args[1]);
            Pool.getDevJar().devJar(Util.getClassPath(""),args[2],args[0]);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }

        return "success";
    }

    @Override
    public String[] usage() {
        return new String[]{"可以对一堆未加密的class文件加密，并打包","main_class 主类,keyFile 加密文件路径(根为${keyfile}) target 目标文件"};
    }
}
