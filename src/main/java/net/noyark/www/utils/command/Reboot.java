package net.noyark.www.utils.command;

import net.noyark.www.utils.ex.ShutDownException;

import java.util.concurrent.CountDownLatch;

public class Reboot implements CommandBase {

    private CountDownLatch latch;

    public Reboot(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public Object excute(String[] args) {
        latch.countDown();
        throw new ShutDownException();
    }
}
