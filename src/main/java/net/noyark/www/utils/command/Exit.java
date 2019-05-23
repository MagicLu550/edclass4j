package net.noyark.www.utils.command;

public class Exit implements CommandBase {

    @Override
    public Object excute(String[] args) {
        System.exit(0);
        return "暂停";
    }
}
