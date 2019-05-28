package net.noyark.www.utils.command.newcommand;

import net.noyark.www.utils.api.Pool;

/**
 * 解密classpath下全部类
 */

public class Adeclassall implements SafeCommand{

    @Override
    public Object execute(String[] args) {

        Pool.getAESClassCoder().recursiveDecode(args[0],args[1]);

        return "解密成功";
    }

    @Override
    public String[] usage() {
        return new String[]{"adeclassall 用于解密classpath下的aes加密class文件","mainClass 运行入口 keyFile 解密文件"};
    }
}
