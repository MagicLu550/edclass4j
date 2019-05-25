package net.noyark.www.utils.command;


import net.noyark.www.utils.api.Connector;
import net.noyark.www.utils.encode.DecryptStart;



/**
 * 将代码解码并判断有没有远程端的key
 * dcclass ip port key classkeyfilename classname...
 * dcclass ip port -f filename keyname classkeyfilename classname...
 */

public class Dcclass extends ConnectorCommand {

    public static final int BEFORE_LENGTH_F = 5;

    public static final int BEFORE_LENGTH = 3;

    public static final int IP_INDEX_F = 0;

    public static final int PORT_INDEX_F = 1;

    public static final int KEY_FILE_INDEX_F = 3;

    public static final int KEY_NAME_INDEX_F = 4;

    public static final int KEY_INDEX = 2;

    public static final int IP_INDEX = 0;

    public static final int PORT_INDEX = 1;

    public Dcclass(Connector connector) {
        super(connector);
    }

    @Override
    public Object execute(String[] args) {
        try{
            if("-f".equals(args[2])){
                //keyname classname;
                String[] deargs = new String[args.length-BEFORE_LENGTH_F];
                System.arraycopy(args,BEFORE_LENGTH_F,deargs,0,deargs.length);
                DecryptStart.decode(deargs,true);
                return connector.compareKey(args[KEY_FILE_INDEX_F],args[KEY_NAME_INDEX_F],args[IP_INDEX_F],Integer.parseInt(args[PORT_INDEX_F]));
            }else{
                String[] deargs = new String[args.length-BEFORE_LENGTH];
                System.arraycopy(args,BEFORE_LENGTH,deargs,0,deargs.length);
                DecryptStart.decode(deargs,true);
                return connector.compareKey(args[KEY_INDEX],args[IP_INDEX],Integer.parseInt(args[PORT_INDEX]));
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    public String[] usage() {
        return new String[]{"解码代码，并判断远程端是否符合，前提连接了远程端","ip 服务器地址 port 端口 key 远端秘钥 class_key_filename 秘钥文件 classname... class字节码文件的包名+类名","ip port -f 选项 filename uuid秘钥的yml文件 key_name yml文件中的键名 class_key_filename classname..."};
    }
}
