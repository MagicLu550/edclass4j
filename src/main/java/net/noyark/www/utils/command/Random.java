package net.noyark.www.utils.command;

import net.noyark.www.utils.Connector;

public class Random extends ConnectorCommand{

    public Random(Connector connector) {
        super(connector);
    }

    @Override
    public Object excute(String[] args) {
        if(!args[0].equals("")){
            connector.randomKeys(Integer.parseInt(args[0]));
        }else{
            connector.randomKey();
        }
        return "生成成功";
    }
}
