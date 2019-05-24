package net.noyark.www.utils.command;

import net.noyark.www.utils.Connector;

public class Ctable extends ConnectorCommand {

    public Ctable(Connector connector) {
        super(connector);
    }

    @Override
    public Object execute(String[] args) {
        connector.createKeyTable(args[0]);
        return "创建成功";
    }
}
