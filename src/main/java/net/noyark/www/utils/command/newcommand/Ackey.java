package net.noyark.www.utils.command.newcommand;

import net.noyark.www.utils.safe.encode.AESGenerateKey;

/**
 * 创建aes专用key
 * ackey keyfile
 */

public class Ackey implements SafeCommand{

    @Override
    public Object execute(String[] args) {
        try{
            AESGenerateKey.createKey(args[0]);
        }catch (Exception e){
            e.printStackTrace();
            return "生成失败";
        }
        return "生成成功";
    }

    @Override
    public String[] usage() {
        return new String[]{"ackey 用于生成aes秘钥","key_file 生成路径"};
    }
}
