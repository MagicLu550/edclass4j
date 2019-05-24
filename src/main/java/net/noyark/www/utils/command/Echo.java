package net.noyark.www.utils.command;


public class Echo implements CommandBase {

    @Override
    public Object execute(String[] args) {
        StringBuilder builder = new StringBuilder();
        for(String arg:args){
            builder.append(arg);
        }
        return builder;
    }
}
