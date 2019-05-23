package net.noyark.www.utils.command;

import net.noyark.www.utils.encode.EncryptClasses;

public class Eclass implements CommandBase{

    @Override
    public Object excute(String[] args) {
        try{
            EncryptClasses.encode(args);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "加密成功";
    }
}
