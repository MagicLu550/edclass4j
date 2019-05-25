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

    @Override
    public String[] usage() {
        return new String[]{"可以生成多个授权码，并插入授权端","number 生成数量"};
    }
}
