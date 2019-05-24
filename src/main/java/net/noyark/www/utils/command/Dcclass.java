package net.noyark.www.utils.command;


import net.noyark.www.utils.Connector;
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
    public Object excute(String[] args) {
        try{
            if("-f".equals(args[2])){
                //keyname classname;
                String[] deargs = new String[args.length-BEFORE_LENGTH_F];
                System.arraycopy(args,BEFORE_LENGTH_F,deargs,0,deargs.length);
                DecryptStart.decode(deargs);
                return connector.compareKey(args[KEY_FILE_INDEX_F],args[KEY_NAME_INDEX_F],args[IP_INDEX_F],Integer.parseInt(args[PORT_INDEX_F]));
            }else{
                String[] deargs = new String[args.length-BEFORE_LENGTH];
                System.arraycopy(args,BEFORE_LENGTH,deargs,0,deargs.length);
                DecryptStart.decode(deargs);
                return connector.compareKey(args[KEY_INDEX],args[IP_INDEX],Integer.parseInt(args[PORT_INDEX]));
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
