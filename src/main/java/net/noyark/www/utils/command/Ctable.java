package net.noyark.www.utils.command;

import net.noyark.www.utils.api.Connector;

public class Ctable extends ConnectorCommand {

    public Ctable(Connector connector) {
        super(connector);
    }

    @Override
    public Object execute(String[] args) {
        connector.createKeyTable(args[0]);
        return "创建成功";
    }

    @Override
    public String[] usage() {
        return new String[]{"用于创建标准的授权存储表","table_name table名称"};
    }
}
