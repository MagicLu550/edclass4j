package net.noyark.www.utils.command;

import net.noyark.www.utils.encode.EncryptClasses;

public class Eclass implements CommandBase{

    @Override
    public Object excute(String[] args) {
        try{
            String[] classes = new String[args.length-1];
            System.arraycopy(args,1,classes,0,classes.length);
            EncryptClasses.encode(args[0],classes);
        }catch (Exception e){
            e.printStackTrace();
            return "加密失败";
        }
        return "加密成功";
    }
}
