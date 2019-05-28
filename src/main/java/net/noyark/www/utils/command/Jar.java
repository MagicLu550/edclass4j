package net.noyark.www.utils.command;


import net.noyark.www.utils.jar.DecodeJar;

/**
 * jar jarfile key_file 加密方式 arg1 arg2 arg3
 */

public class Jar implements CommandBase {

    @Override
    public Object execute(String[] args) {
        DecodeJar jar = new DecodeJar(args[0],args[1], args[2]);
        String[] realArgs = new String[args.length-3];
        System.arraycopy( args, 3, realArgs, 0, args.length-3 );
        try{
            jar.runJar(realArgs);
        }catch (Exception e){
            e.printStackTrace();
            return "Process finished with exit code -1";
        }
        return "Process finished with exit code 0";
    }

    @Override
    public String[] usage() {
        return new String[]{"运行加密jar包","jar_file jar包路径 key_file 加密方式(AES/DES) 加密key args... 主main方法参数"};
    }
}
