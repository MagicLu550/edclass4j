package net.noyark.www.utils.command;

import net.noyark.www.utils.api.DevTool;
import net.noyark.www.utils.api.Pool;
import net.noyark.www.utils.encode.Util;

/**
 * 打包jar包的指令,默认为application的指定类路径
 * dev target
 * dev classpath target
 * dev -m classpath target main
 * dev -m target main
 * 也可以指定mainClass
 */
public class Dev implements CommandBase {

    @Override
    public Object execute(String[] args) {
        try{
            DevTool tool = Pool.getDevJar();
            if(args[0].equals("-m")){
                if(args.length==3){
                    //-m target main
                    tool.devJar(Util.getClassPath(""),args[1],args[2]);
                }else if(args.length==4){
                    tool.devJar(args[1],args[2],args[3]);
                }else{
                    return "打包失败";
                }
            }else{
                if(args.length==1){
                    tool.devJar(Util.getClassPath(""),args[0],null);
                }else{
                    tool.devJar(args[0],args[1],null);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "打包失败";
        }
        return "success";
    }

    @Override
    public String[] usage() {
        return new String[]{"打包成jar","target 目标路径","classpath 指定打包根路径,target 目标路径","-m选项 在前面基础上可以添加主运行类"};
    }
}
