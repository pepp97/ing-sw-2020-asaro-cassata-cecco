package it.polimi.ingsw.model;

import java.util.TimerTask;

public class TimeoutCounter extends TimerTask {
    TimeoutCheckerInterface timeoutChecker;

    public TimeoutCounter(TimeoutCheckerInterface timeoutChecker) {
        this.timeoutChecker = timeoutChecker;
    }



    public static long l=0;

    @Override
    public void run() {
        boolean stop=timeoutChecker.check(++l);
        if(stop){
            l=0;
            this.cancel();
        }
    }
}
