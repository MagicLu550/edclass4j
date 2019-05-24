package net.noyark.www.utils.command;

import net.noyark.www.utils.api.Connector;

public abstract class ConnectorCommand implements CommandBase {

    protected Connector connector;

    public ConnectorCommand(Connector connector){
        this.connector = connector;
    }

}
