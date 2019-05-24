package net.noyark.www.utils.command;

import net.noyark.www.utils.api.Connector;

public class Random extends ConnectorCommand{

    public Random(Connector connector) {
        super(connector);
    }

    @Override
    public Object execute(String[] args) {
        if(!args[0].equals("")){
            connector.randomKeys(Integer.parseInt(args[0]));
        }else{
            connector.randomKey();
        }
        return "生成成功";
    }
}
