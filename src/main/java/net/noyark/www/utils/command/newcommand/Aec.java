package net.noyark.www.utils.command.newcommand;

import net.noyark.www.utils.safe.encode.SafeEncryptClasses;

/**
 * aec 以aes-base64标准加密一个类
 * aec key_file classname1 classname2
 */

public class Aec implements SafeCommand{

    @Override
    public Object execute(String[] args) {
        String[] classes = new String[args.length-1];
        System.arraycopy(args,1,classes,0,classes.length);
        SafeEncryptClasses safeEncryptClasses = new SafeEncryptClasses();
        safeEncryptClasses.encode(args[0],classes);
        return "加密成功";
    }

    @Override
    public String[] usage() {
        return new String[]{"aec 加密为aes类型","key_file 秘钥文件 classname1 classname2"};
    }
}
