package net.noyark.www.utils.command;

import net.noyark.www.utils.encode.GenerateKey;

public class Cckey implements CommandBase{

    @Override
    public Object excute(String[] args) {
        try{
            GenerateKey.createKey(args);
        }catch (Exception e){
            e.printStackTrace();
            return "生成密钥文件过程中发生未知错误";
        }

        return "生成秘钥文件";
    }
}
