package net.noyark.www.utils.command;

import net.noyark.www.utils.encode.DecryptStart;

public class Dclass implements CommandBase {

    @Override
    public Object execute(String[] args) {
        try{
            DecryptStart.decode(args,true);
        }catch (Exception e){
            e.printStackTrace();
            return "解码失败,缺少主要参数";
        }
        return "解码成功";
    }

    @Override
    public String[] usage() {
        return new String[]{"解码单个class文件","class_key_file 解码key文件名 classname class包名+类名"};
    }
}
